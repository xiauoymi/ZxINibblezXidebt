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
import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.Nibbler;
import com.nibbledebt.core.data.model.NibblerAccount;
import com.nibbledebt.core.data.model.NibblerDirectoryStatus;
import com.nibbledebt.dwolla.IDwollaClient;
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

	@Scheduled(cron = "0 0 * * 1 *")
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	public void processPayment() throws RepositoryException {
		List<Nibbler> nibblers = nibblerDao.findByStatus(NibblerDirectoryStatus.ACTIVE.name(),NibblerDirectoryStatus.ACTIVE_NO_ROUNDUP.name(),NibblerDirectoryStatus.ACTIVE_NO_LOAN_ACCT.name());
		StringBuilder error = new StringBuilder();
		nibblers.forEach(nibbler -> {
			Map<String, NibblerAccount> sources = new HashMap<String, NibblerAccount>();
			List<String> destinations = new ArrayList<String>();
			Map<NibblerAccount, BigDecimal> payments = new HashMap<>();
			Map<NibblerAccount, BigDecimal> fees = new HashMap<>();
			Map<String,BigDecimal> balances=new HashMap<>();
			BigDecimal triggerAmount = nibbler.getNibblerPreferences().getWeeklyTargetAmount();
			System.out.println("nibbler "+nibbler.getId());
			nibbler.getAccounts().forEach(account -> {
				if (account.getUseForRounding() && Strings.isNotEmpty(account.getFundingSourceId())) {
					try {
						if (!StringUtils.equalsIgnoreCase(env.getActiveProfiles()[0], "prod")){
							balances.put(account.getFundingSourceId(), randomBalance());
						}else{
							balances.put(account.getFundingSourceId(), dwollaClient.getBalance(account.getFundingSourceId()));
						}
						if (payFee(nibbler) || account.getBalanceBelow20() > 0)
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
								dwollaClient.payFee(account.getFundingSourceId(),
										account.getNibbler().getNibblerPreferences().getFeeAmount().toString(),
										account);
								fees.put(account, account.getNibbler().getNibblerPreferences().getFeeAmount());
							}
					} catch (Exception e) {
						e.printStackTrace();
						
						error.append("<br>Account: ").append(account.getNumber()).append(" error message :")
								.append(e.getMessage());
					}
					if (account.getCumulativeRoundupsAmount().compareTo(triggerAmount) > 0) {
						System.out.println("sources "+account.getFundingSourceId());
						sources.put(account.getFundingSourceId(), account);
					}
				} else if (account.getUseForpayoff() && Strings.isNotEmpty(account.getDwollaLoanId())){
					try {
						System.out.println("destinations "+account.getDwollaLoanId());
						destinations.add(account.getDwollaLoanId());
					} catch (Exception e) {
						error.append("<br>Account: ").append(account.getNumber()).append(" error message :")
								.append(e.getMessage());
					}
				}
			});
			sources.forEach((k, v) -> {
				if (destinations!=null && destinations.size() > 0) {
					try {
						String destination = destinations.get(0);
						//BigDecimal balance = balances.get(destination);
						if(dwollaClient.transfer(v.getFundingSourceId(), destination, triggerAmount.toString(), v))
						v.setCumulativeRoundupsAmount(v.getCumulativeRoundupsAmount().subtract(triggerAmount));
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
			sendErrorReport(error.toString());
		}
	}

	public void processWeeklyPaymentForUser(Nibbler nibbler) {

	}
	private Random rand = new Random();
	private BigDecimal randomBalance(){
		//double[] big={1.5,100,20,21,25,30,0,-1,300};
		double[] big={100,25,300};
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
			if(!payments.isEmpty()){
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
			toEmails.add("admin@nibbledebt.com");
			toEmails.add("m.boutaskiouine@gmail.com");
			toEmails.add("jalexander.hc.317@gmail.com");
			VelocityContext acCtx = new VelocityContext();
			Template acTmpl = velocityEngineFactory.createVelocityEngine().getTemplate("error-payment.vm");
			acCtx.put("error", error);
			StringWriter acWriter = new StringWriter();
			acTmpl.merge(acCtx, acWriter);
			awsMailSao.sendEmail("Report Error Payment", acWriter.toString(), toEmails);
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

	private boolean payFee(Nibbler n) {
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
}
