/**
 * Copyright 2015-2017. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.processor;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.ws.rs.ProcessingException;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

import com.nibbledebt.common.notifier.Notify;
import com.nibbledebt.common.notifier.NotifyMethod;
import com.nibbledebt.common.notifier.NotifyType;
import com.nibbledebt.core.data.dao.INibblerDao;
import com.nibbledebt.core.data.dao.IPaymentActivityDao;
import com.nibbledebt.core.data.dao.NibblerAccountDao;
import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.Nibbler;
import com.nibbledebt.core.data.model.NibblerAccount;
import com.nibbledebt.core.data.model.NibblerDirectoryStatus;
import com.nibbledebt.core.data.model.PaymentActivity;
import com.nibbledebt.dwolla.IDwollaClient;
import com.nibbledebt.dwolla.TypePayment;
import com.nibbledebt.integration.sao.IIntegrationSao;
import com.nibbledebt.integration.sao.mandrill.AWSMailSao;

import io.swagger.client.ApiException;

/**
 * @author Rocky Alam
 *
 */
@Component
public class BillingProcessor extends AbstractProcessor {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private INibblerDao nibblerDao;

	@Autowired
	@Qualifier("finicitySao")
	private IIntegrationSao integrationSao;

	@Autowired
	private IDwollaClient dwollaClient;

	@Resource
	private Environment env;
	
	@Autowired
	private AWSMailSao awsMailSao;

	@Autowired
	private VelocityEngineFactoryBean velocityEngineFactory;
	
	@Autowired
	private IPaymentActivityDao paymentActivityDao;

	@Autowired
	private NibblerAccountDao nibblerAccountDao;
	@Scheduled(cron = "0 0 * * 1 *")
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	public void processPayment()  {
		try {
			List<Nibbler> nibblers = nibblerDao.findByStatus(NibblerDirectoryStatus.ACTIVE.name());
			StringBuilder error = new StringBuilder();
			StringBuilder successPayement=new StringBuilder();
			Map<String,BigDecimal> payementByCustomer= new HashMap<>();
			Map<String,BigDecimal> payementByBank= new HashMap<>();
			Map<String,BigDecimal> payementByLoan= new HashMap<>();
			nibblers.forEach(nibbler -> {
				Map<String, NibblerAccount> sources = new HashMap<String, NibblerAccount>();
				List<NibblerAccount> destinations = new ArrayList<NibblerAccount>();
				Map<NibblerAccount, BigDecimal> payments = new HashMap<>();
				Map<NibblerAccount, BigDecimal> fees = new HashMap<>();
				Map<String,BigDecimal> balances=new HashMap<>();
				BigDecimal triggerAmount = nibbler.getNibblerPreferences().getWeeklyTargetAmount();
				logger.info("nibbler "+nibbler.getId());
				nibbler.getAccounts().forEach(account -> {
					if (account.getUseForRounding() && Strings.isNotEmpty(account.getFundingSourceId())) {
						try {
							if (!StringUtils.equalsIgnoreCase(env.getActiveProfiles()[0], "prod")){
								balances.put(account.getFundingSourceId(), randomBalance());
							}else{
								balances.put(account.getFundingSourceId(), dwollaClient.getBalance(account.getFundingSourceId()));
							}
							if (is2PayFee(nibbler) || account.getBalanceBelow20() > 0)
								if (balances.get(account.getFundingSourceId()).compareTo(new BigDecimal("20")) < 0) {
									if (account.getLastCheckingBalance() != null) {
										int diffDay = Days.daysBetween(new DateTime(new Date()),
												new DateTime(account.getLastCheckingBalance())).getDays();
										if (diffDay == 9) {
											nibbler.getNibblerDir().setStatus(NibblerDirectoryStatus.SUSPEND.name());
											nibbler.getNibblerDir().setLastUpdateStatus(new Date());
											sendErrorFee(null);
										}
									} else {
										account.setLastCheckingBalance(new Date());
										account.setBalanceBelow20(1);
									}
								} else {
									account.setLastCheckingBalance(null);
									account.setBalanceBelow20(0);
									payFee(account,
											account.getNibbler().getNibblerPreferences().getFeeAmount());
									fees.put(account, account.getNibbler().getNibblerPreferences().getFeeAmount());
								}
						} catch (Exception e) {
							e.printStackTrace();
							
							error.append("<br>Account: ").append(account.getNumber()).append(" error message :")
									.append(e.getMessage());
						}
						if (account.getCumulativeRoundupsAmount().compareTo(triggerAmount) > 0) {
							logger.info("sources "+account.getFundingSourceId());
							sources.put(account.getFundingSourceId(), account);
						}
					} else if (account.getUseForpayoff() && Strings.isNotEmpty(account.getDwollaLoanId())){
						try {
							logger.info("destinations "+account.getDwollaLoanId());
							destinations.add(account);
						} catch (Exception e) {
							error.append("<br>Account: ").append(account.getNumber()).append(" error message :")
									.append(e.getMessage());
						}
					}
				});
				sources.forEach((k, v) -> {
					if (destinations!=null && destinations.size() > 0) {
						try {
							String destination = destinations.get(0).getDwollaLoanId();
							//BigDecimal balance = balances.get(destination);
							String authorizationCode=dwollaClient.transfer(v.getFundingSourceId(), destination, triggerAmount.toString(), v);
							if(authorizationCode!=null){
								PaymentActivity paymentActivity = new PaymentActivity();
								paymentActivity.setAmount(triggerAmount);
								paymentActivity.setAuthorizationCode(authorizationCode);
								paymentActivity.setFromAccount(v);
								paymentActivity.setToAccount(destinations.get(0));
								paymentActivity.setInitiatedTs(new Date());
								paymentActivity.setCreatedTs(new Date());
								paymentActivity.setCreatedUser("sysuser");
								paymentActivityDao.saveOrUpdate(paymentActivity);
								v.setCumulativeRoundupsAmount(v.getCumulativeRoundupsAmount().subtract(triggerAmount));
							}
							addReportPayement(payementByBank, v.getInstitution().getSupportedInstitution().getDisplayName(), triggerAmount);
							addReportPayement(payementByCustomer, v.getNibbler().getEmail(), triggerAmount);
							addReportPayement(payementByLoan, destinations.get(0).getNibbler().getEmail(), triggerAmount);
							payments.put(v, triggerAmount);
//						if (balance.compareTo(new BigDecimal("0")) > 0) {
//							if (balance.compareTo(triggerAmount) > 0) {
//								dwollaClient.transfer(v.getFundingSourceId(), destination, triggerAmount.toString(), v);
//								v.setCumulativeRoundupsAmount(v.getCumulativeRoundupsAmount().subtract(triggerAmount));
//								payments.put(v, triggerAmount);
//							} else {
//								dwollaClient.transfer(v.getFundingSourceId(), destination, balance.negate().toString(),
//										v);
//								v.setCumulativeRoundupsAmount(v.getCumulativeRoundupsAmount().subtract(balance));
//								payments.put(v, balance);
//							}
//						} else {
//							destinations.remove(0);
//						}
						} catch (Exception e) {
							error.append("<br>Account: ").append(v.getNumber()).append(" error message :")
									.append(e.getMessage());
						}
					}
				});
				try {
					nibblerDao.update(nibbler);
					transfer(payments, fees,nibbler.getEmail());
				} catch (Exception e) {
					error.append("<br>Nibbler: ").append(nibbler.getEmail()).append(" error message :").append(e.getMessage());
				}
			});
			if (error.length() > 0) {
				successPayement.append("Daily summary of All money <br>");
				buildReportPayment(payementByCustomer, successPayement,"- Break down by customer <br>");
				buildReportPayment(payementByLoan, successPayement, "- Break down by loan provider <br>");
				buildReportPayment(payementByBank, successPayement, "- Break down by funding bank <br>");
				successPayement.append("<br>").append(error);
				sendErrorReport(successPayement.toString());
			}
		} catch (RepositoryException e) {
			logger.error(e.getMessage());
		}
	}

	private void addReportPayement(Map<String,BigDecimal> map,String k,BigDecimal v) {
		if(map.containsKey(k)){
			map.put(k, map.get(k).add(v));
		}else{
			map.put(k, v);
		}
	}
	private void buildReportPayment(Map<String,BigDecimal> payementByBank,StringBuilder successPayement,String title){
		if(payementByBank.size()>0){
			successPayement.append(title);
			payementByBank.forEach((k,v)->{
				successPayement.append("- ").append(k).append(" = ").append(v).append("<br>");
			});
		}

	}
	private Random rand = new Random();
	private BigDecimal randomBalance(){
		double[] big={1.5,100,20,21,25,30,0,-1,300};
		//double[] big={100,25,300};
		int randomNum = rand.nextInt(big.length);
		return new BigDecimal(big[randomNum]);
	}
	
	@Notify(notifyMethod = NotifyMethod.EMAIL, notifyType = NotifyType.LOAN_PAYMENT)
	public void transfer(Map<NibblerAccount, BigDecimal> payments, Map<NibblerAccount, BigDecimal> fees,String email)
			throws ApiException {
		try {
			List<String> toEmails = new ArrayList<String>();
			toEmails.add("m.boutaskiouine@gmail.com");
			toEmails.add("jalexander.hc.317@gmail.com");
			toEmails.add("admin@nibbledebt.com");
			if(!payments.isEmpty() || !fees.isEmpty()){
				toEmails.add(email);
					VelocityContext acCtx = new VelocityContext();
					Template acTmpl = velocityEngineFactory.createVelocityEngine().getTemplate("loan-payment.vm");
					String date=DateFormatUtils.format(new Date(), "yyyy-dd-MM");
					StringBuilder paymentString=new StringBuilder();
					StringBuilder feesString=new StringBuilder();
					payments.forEach((k,v)->{
						paymentString.append("Payment Date: ").append(date).append("<br>");
						paymentString.append("Payment amount: $ ").append(v).append("<br>");
						paymentString.append("Loan Account: ").append(k.getInstitution().getSupportedInstitution().getDisplayName()).append("<br>");
					});
					fees.forEach((k,v)->{
						feesString.append("Payment Fee: :$ ").append(v).append("<br>");
					});
					acCtx.put("payments", paymentString);
					acCtx.put("fees", feesString);
					StringWriter acWriter = new StringWriter();
					acTmpl.merge(acCtx, acWriter);
					awsMailSao.sendEmail("You’re making progress! A Nibble payment has been made to your student loan account", acWriter.toString(), toEmails);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Notify(notifyMethod = NotifyMethod.EMAIL, notifyType = NotifyType.ERROR_PAYMENT)
	public String sendErrorReport(String error) {
		logger.info(error);
		try {
			List<String> toEmails = new ArrayList<String>();
			toEmails.add("jalexander.hc.317@gmail.com");
			toEmails.add("admin@nibbledebt.com");
			toEmails.add("m.boutaskiouine@gmail.com");
			//toEmails.add("jalexander.hc.317@gmail.com");
			VelocityContext acCtx = new VelocityContext();
			Template acTmpl = velocityEngineFactory.createVelocityEngine().getTemplate("error-payment.vm");
			acCtx.put("error", error);
			StringWriter acWriter = new StringWriter();
			acTmpl.merge(acCtx, acWriter);
			awsMailSao.sendEmail("Daily summary of All money Payment", acWriter.toString(), toEmails);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return error;
	}

	@Notify(notifyMethod = NotifyMethod.EMAIL, notifyType = NotifyType.ERROR_FEE)
	public String sendErrorFee(String error) {
		logger.info(error);
		try {
		List<String> toEmails = new ArrayList<String>();
		toEmails.add("admin@nibbledebt.com");
		toEmails.add("m.boutaskiouine@gmail.com");
		toEmails.add("jalexander.hc.317@gmail.com");
		VelocityContext acCtx = new VelocityContext();
		Template acTmpl = velocityEngineFactory.createVelocityEngine().getTemplate("error-fee.vm");
		StringWriter acWriter = new StringWriter();
		acTmpl.merge(acCtx, acWriter);
		awsMailSao.sendEmail("There was a problem with your Nibble account", acWriter.toString(), toEmails);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return error;
	}
	
	public void sendWeeklyReport(){
		try {
		List<String> toEmails = new ArrayList<String>();
		toEmails.add("m.boutaskiouine@gmail.com");
		toEmails.add("jalexander.hc.317@gmail.com");
		toEmails.add("admin@nibbledebt.com");
		VelocityContext acCtx = new VelocityContext();
		Template acTmpl = velocityEngineFactory.createVelocityEngine().getTemplate("weekly-report.vm");
		String logo= "http://localhost:9000/images/logo1.png";
		StringWriter acWriter = new StringWriter();
		acCtx.put("logo", logo);
		acTmpl.merge(acCtx, acWriter);
		awsMailSao.sendEmail("You’re making progress! Here’s your weekly Nibble summary!", acWriter.toString(), toEmails);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	private boolean is2PayFee(Nibbler n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(n.getCreatedTs());
		int createdDay = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(new Date());
		int nowDay = cal.get(Calendar.DAY_OF_MONTH);
		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		if (createdDay == nowDay) {
			return true;
		}
		if (maxDay < createdDay && maxDay == nowDay) {
			return true;
		}
		return true;
		//TODO return false 
	}
	
	private void payFee(NibblerAccount destination,BigDecimal triggerAmount) throws RepositoryException, ApiException{
		NibblerAccount source=nibblerAccountDao.findByExternalId(dwollaClient.getFundingSource());
		String authorizationCode=dwollaClient.transfer(source.getFundingSourceId(), destination.getFundingSourceId(), triggerAmount.toString(), source);
		if(authorizationCode!=null){
			PaymentActivity paymentActivity = new PaymentActivity();
			paymentActivity.setAmount(triggerAmount);
			paymentActivity.setAuthorizationCode(authorizationCode);
			paymentActivity.setFromAccount(source);
			paymentActivity.setToAccount(destination);
			paymentActivity.setInitiatedTs(new Date());
			paymentActivity.setCreatedTs(new Date());
			paymentActivity.setCreatedUser("sysuser");
			paymentActivity.setType(TypePayment.FEE.name());
			paymentActivityDao.saveOrUpdate(paymentActivity);
		}
	}
	
	@Transactional
	public BigDecimal refundFee(String username) throws ProcessingException{
		try {
			List<PaymentActivity> toRefunds=paymentActivityDao.getByType(username, TypePayment.FEE.name());
			BigDecimal sum=new BigDecimal("0");
			PaymentActivity paymentActivity = new PaymentActivity();
			toRefunds.stream().forEach(pa->{
				if(paymentActivity.getFromAccount()==null){
					paymentActivity.setToAccount(pa.getFromAccount());
					paymentActivity.setFromAccount(pa.getToAccount());
				}
						
				pa.setType(TypePayment.REFUNDED_FEE.name());
				sum.add(pa.getAmount());
			});
			if(sum.compareTo(BigDecimal.ZERO)>0){
				String authorizationCode=dwollaClient.transfer(paymentActivity.getFromAccount().getFundingSourceId(), paymentActivity.getToAccount().getFundingSourceId(), sum.toString(), paymentActivity.getToAccount());
				paymentActivity.setAuthorizationCode(authorizationCode);
				paymentActivity.setInitiatedTs(new Date());
				paymentActivity.setCreatedTs(new Date());
				paymentActivity.setCreatedUser("sysuser");
				paymentActivity.setType(TypePayment.REFUND_FEE.name());
				paymentActivity.setAmount(sum);
				paymentActivityDao.saveOrUpdate(paymentActivity);	
			}
			return sum;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ProcessingException("Error while refunding fee");
		}
		
	}
	
}
