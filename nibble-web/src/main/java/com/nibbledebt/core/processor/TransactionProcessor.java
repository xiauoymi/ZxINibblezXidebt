/**
 * 
 */
package com.nibbledebt.core.processor;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.jfree.chart.JFreeChart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

import com.nibbledebt.common.error.NotificationException;
import com.nibbledebt.common.error.ProcessingException;
import com.nibbledebt.common.error.ServiceException;
import com.nibbledebt.common.logging.LogLevel;
import com.nibbledebt.common.logging.Loggable;
import com.nibbledebt.core.data.dao.IAccountTransactionDao;
import com.nibbledebt.core.data.dao.IChartImageDao;
import com.nibbledebt.core.data.dao.IDao;
import com.nibbledebt.core.data.dao.INibblerAccountDao;
import com.nibbledebt.core.data.dao.INibblerDao;
import com.nibbledebt.core.data.dao.ITransactionCategoryDao;
import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.AccountTransaction;
import com.nibbledebt.core.data.model.ChartImage;
import com.nibbledebt.core.data.model.Location;
import com.nibbledebt.core.data.model.Nibbler;
import com.nibbledebt.core.data.model.NibblerAccount;
import com.nibbledebt.core.data.model.NibblerDirectoryStatus;
import com.nibbledebt.core.data.model.NibblerType;
import com.nibbledebt.core.data.model.TransactionCategory;
import com.nibbledebt.domain.model.Transaction;
import com.nibbledebt.domain.model.TransactionSummary;
import com.nibbledebt.integration.finicity.model.Customer;
import com.nibbledebt.integration.finicity.model.Customers;
import com.nibbledebt.integration.sao.IIntegrationSao;
import com.nibbledebt.integration.sao.mandrill.AWSMailSao;
import com.nibbledebt.web.chart.FactoryChart;

/**
 * @author Rocky Alam
 *
 */
@Component
public class TransactionProcessor extends AbstractProcessor {
	private static final String SYS_USER = "sys_user";
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private INibblerDao nibblerDao;
	
	@Autowired
	private IDao<AccountTransaction> accountTrasactionDao;

	@Autowired
	private IAccountTransactionDao accountTrxDao;

	@Autowired
	private INibblerAccountDao nibblerAcctDao;

	@Autowired
	@Qualifier("finicitySao")
	private IIntegrationSao integrationSao;

	@Resource
	private Environment env;

	@Autowired
	private AWSMailSao awsMailSao;

	@Autowired
	private VelocityEngineFactoryBean velocityEngineFactory;

	@Autowired
	private ITransactionCategoryDao trxCatDao;

	@Autowired
	private IChartImageDao chartImageDao;

	@Autowired
	private AccountsProcessor accountProcessor;

	public static void main(String[] args) {
		//logger.info(System.currentTimeMillis() / 1000);
	}

	@Transactional
	public TransactionSummary getWeeklyTrxSummary(String username,boolean dummy)
			throws ProcessingException, RepositoryException, IOException {
		TransactionSummary summary = new TransactionSummary();
		Nibbler nibbler = nibblerDao.find(username);
		summary.setPersonFirstName(nibbler.getFirstName());
		summary.setPersonLastName(nibbler.getLastName());
		summary.setPersonId(nibbler.getId());
		summary.setEmail(nibbler.getEmail());
		summary.setReferral(nibbler.getReferral());
		summary.setLoanSummary(accountProcessor.getLoanSummary(username));
		LocalDate now = LocalDate.now();
		if (nibbler.getContributors().size()>0) {
			calculateSummary(summary, nibbler, now, false);
			List<Nibbler> contributors = nibblerDao.findContributors(nibbler.getId());
			for (Nibbler contributor : contributors) {
				calculateSummary(summary, contributor, now, true);
			}
		} else {
			calculateSummary(summary, nibbler, now, false);
		}

		if (dummy) {
		//if (!StringUtils.equalsIgnoreCase(env.getActiveProfiles()[0], "prod")) {
			Map<String, BigDecimal> map = new HashMap<>();
			map.put("WANG NYRT", BigDecimal.TEN);
			map.put("JACK ROSSI", new BigDecimal("14"));
			map.put("JOE BERNARD", new BigDecimal("3"));
			map.put("HENRI SOK", new BigDecimal("30"));
			map.put("AQUM PLOR", new BigDecimal("43"));
			Long pie = saveChart(FactoryChart.createRingChart(map));
			Long bar = saveChart(
					FactoryChart.createChart(new BigDecimal("334"), new BigDecimal("100")));
			summary.setPieChartId(pie);
			summary.setBarChartId(bar);
			summary.setReferral("12355");
			return summary;
		} else {
			if(!summary.getContributorSummaries().isEmpty()){
				Map<String, BigDecimal> map = summary.getContributorSummaries().stream()
						.collect(Collectors.toMap(TransactionSummary::getFullName, TransactionSummary::getTotalPayment));
				Long pie = saveChart(FactoryChart.createRingChart(map));	
				summary.setPieChartId(pie);
			}
			Long bar = saveChart(
					FactoryChart.createChart(summary.getTotalPayment(), summary.getTotalPaymentContributors()));
			summary.setBarChartId(bar);
			return summary;
		}

		
	}

	@Scheduled(cron = "* * * * * 6")
	@Transactional
	public void buildWeeklyReport() throws RepositoryException {
		List<Nibbler> nibblers = nibblerDao.findByStatus(NibblerDirectoryStatus.ACTIVE.name());
				nibblers.forEach(nibbler -> {
			try {
				sendWeeklyTrxSummary(getWeeklyTrxSummary(nibbler.getNibblerDir().getUsername(),false));

			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	
	@Transactional
	public void buildDummyReport() throws RepositoryException, NotificationException, ProcessingException, IOException {
		List<Nibbler> nibblers = nibblerDao.findAll();
		sendWeeklyTrxSummary(getWeeklyTrxSummary(nibblers.get(0).getNibblerDir().getUsername(),true));
	}

	@Transactional
	public void buildWeeklyReport(String username) throws ProcessingException{
		try {
			sendWeeklyTrxSummary(getWeeklyTrxSummary(username,false));
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ProcessingException("Error while trying to send report");
		} 
	}
	
	private Long saveChart(JFreeChart chart) throws RepositoryException, IOException {
		BufferedImage bi = chart.createBufferedImage(500, 500);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(bi, "png", out);
		ChartImage chartImage = new ChartImage();
		chartImage.setContent(out.toByteArray());
		chartImage.setCreatedTs(new Date());
		chartImage.setCreatedUser("sysuser");
		chartImageDao.saveOrUpdate(chartImage);
		logger.info("createChart==> " + chartImage.getId());
		return chartImage.getId();
	}
	@Transactional
	private TransactionSummary calculateSummary(TransactionSummary summary, Nibbler nibbler, LocalDate now,
			Boolean asContributor) throws RepositoryException, ProcessingException {
		List<com.nibbledebt.domain.model.Transaction> wtrxs = new ArrayList<>();
		BigDecimal weeklyTotal = BigDecimal.ZERO;
		BigDecimal target = nibbler.getNibblerPreferences().getWeeklyTargetAmount();
		TransactionSummary contributorSummary = null;
		for (NibblerAccount nacct : nibbler.getAccounts()) {
			if (nacct.getUseForRounding()) {
				for (int i = 1; i < 8; i++) {
					BigDecimal dailyTotal = BigDecimal.ZERO;
					TemporalField fieldUS = WeekFields.of(Locale.US).dayOfWeek();
					Date dayStart = Date
							.from(Instant.from((now.with(fieldUS, i).atStartOfDay().atZone(ZoneId.systemDefault()))));
					Date dayEnd = Date
							.from(Instant.from((now.with(fieldUS, i).atStartOfDay().atZone(ZoneId.systemDefault())))
									.plusMillis(86399999));
					List<AccountTransaction> trxsForDay = accountTrxDao.retrieveTrxs(nacct.getId(), dayStart, dayEnd);
					for (AccountTransaction trx : trxsForDay) {
						com.nibbledebt.domain.model.Transaction dtrx = new com.nibbledebt.domain.model.Transaction();
						dtrx.setCity(trx.getLocation().getCity());
						dtrx.setRoundupAmount(trx.getRoundupAmount());
						dtrx.setState(trx.getLocation().getState());
						dtrx.setTrxAmount(trx.getAmount());
						dtrx.setTrxDate(trx.getDate());
						dtrx.setTrxId(trx.getTransactionId());
						dtrx.setAccountNumber(trx.getAccount().getNumberMask());
						dtrx.setInstitutionName(
								trx.getAccount().getInstitution().getSupportedInstitution().getDisplayName());
						dtrx.setDescription(trx.getLocation().getName());
						dailyTotal = dailyTotal.add(dtrx.getRoundupAmount());
						weeklyTotal = weeklyTotal.add(dtrx.getRoundupAmount());
						wtrxs.add(dtrx);
					}
					if (!asContributor) {
						if (i == 1)
							summary.setDay0total(summary.getDay0total().add(dailyTotal));
						else if (i == 2)
							summary.setDay1total(summary.getDay1total().add(dailyTotal));
						else if (i == 3)
							summary.setDay2total(summary.getDay2total().add(dailyTotal));
						else if (i == 4)
							summary.setDay3total(summary.getDay3total().add(dailyTotal));
						else if (i == 5)
							summary.setDay4total(summary.getDay4total().add(dailyTotal));
						else if (i == 6)
							summary.setDay5total(summary.getDay5total().add(dailyTotal));
						else if (i == 7)
							summary.setDay6total(summary.getDay6total().add(dailyTotal));
					} else {
						if (contributorSummary == null)
							contributorSummary = new TransactionSummary();
						if (i == 1)
							contributorSummary.setDay0total(contributorSummary.getDay0total().add(dailyTotal));
						else if (i == 2)
							contributorSummary.setDay1total(contributorSummary.getDay1total().add(dailyTotal));
						else if (i == 3)
							contributorSummary.setDay2total(contributorSummary.getDay2total().add(dailyTotal));
						else if (i == 4)
							contributorSummary.setDay3total(contributorSummary.getDay3total().add(dailyTotal));
						else if (i == 5)
							contributorSummary.setDay4total(contributorSummary.getDay4total().add(dailyTotal));
						else if (i == 6)
							contributorSummary.setDay5total(contributorSummary.getDay5total().add(dailyTotal));
						else if (i == 7)
							contributorSummary.setDay6total(contributorSummary.getDay6total().add(dailyTotal));
					}
				}
				if (!asContributor) {
					summary.setTotalAmountPaid(
							summary.getTotalAmountPaid().add((nacct.getCumulativeRoundupsAmount() == null
									? BigDecimal.ZERO : nacct.getCumulativeRoundupsAmount())));
				} else {
					if (contributorSummary != null)
						contributorSummary.setTotalAmountPaid(
								contributorSummary.getTotalAmountPaid().add((nacct.getCumulativeRoundupsAmount() == null
										? BigDecimal.ZERO : nacct.getCumulativeRoundupsAmount())));
				}
			}
		}
		if (!asContributor) {
			summary.setCurrentWeekAmount(weeklyTotal);
			summary.setCurrentTargetPercent(
					weeklyTotal.divide(target, 2, RoundingMode.UP).multiply(new BigDecimal("100")).intValue());
			summary.setWeeklyTarget(target);
			summary.setTrxs(wtrxs);
		} else {
			if (contributorSummary != null) {
				contributorSummary.setEmail(nibbler.getEmail());
				contributorSummary.setPersonFirstName(nibbler.getFirstName());
				contributorSummary.setPersonLastName(nibbler.getLastName());
				contributorSummary.setCurrentWeekAmount(weeklyTotal);
				summary.setTotalAmountContributors(summary.getTotalAmountContributors().add(contributorSummary.getTotalAmountPaid()));
				contributorSummary.setCurrentTargetPercent(
						weeklyTotal.divide(target, 2, RoundingMode.UP).multiply(new BigDecimal("100")).intValue());
				contributorSummary.setWeeklyTarget(target);
				contributorSummary.setTrxs(wtrxs);
				contributorSummary.setPersonFirstName(nibbler.getFirstName());
				contributorSummary.setPersonLastName(nibbler.getLastName());
				contributorSummary.setPersonId(nibbler.getId());
				contributorSummary.setLoanSummary(accountProcessor.getLoanSummary(nibbler.getEmail()));
				summary.getContributorSummaries().add(contributorSummary);
			}
		}
		return summary;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	public List<com.nibbledebt.domain.model.Transaction> retrieveTransactions(Long accountId, Long sinceDays)
			throws ProcessingException, RepositoryException {
		List<AccountTransaction> transactions;
		if (sinceDays > 0) {
			// LocalTime midnight = LocalTime.MIDNIGHT;
			// LocalDate today = LocalDate.now(ZoneId.systemDefault());

			Date to = Date.from(Instant.now());
			Date from = Date.from(Instant.now().minus(sinceDays, ChronoUnit.DAYS));
			transactions = accountTrxDao.retrieveTrxs(accountId, from, to);
		} else {

			transactions = accountTrxDao.retrieveTrxs(accountId);
		}

		List<com.nibbledebt.domain.model.Transaction> wtrxs = new ArrayList<>();
		for (AccountTransaction trx : transactions) {
			com.nibbledebt.domain.model.Transaction dtrx = new com.nibbledebt.domain.model.Transaction();
			dtrx.setCity(trx.getLocation().getCity());
			dtrx.setRoundupAmount(trx.getRoundupAmount());
			dtrx.setState(trx.getLocation().getState());
			dtrx.setTrxAmount(trx.getAmount());
			dtrx.setTrxDate(trx.getDate());
			dtrx.setTrxId(trx.getTransactionId());
			dtrx.setAccountNumber(trx.getAccount().getNumberMask());
			dtrx.setInstitutionName(trx.getAccount().getInstitution().getSupportedInstitution().getDisplayName());
			dtrx.setDescription(trx.getLocation().getName());
			wtrxs.add(dtrx);
		}
		return wtrxs;
	}

//	@Scheduled(fixedDelay = 6000000)
	@Loggable(logLevel = LogLevel.INFO)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void calculateTransactionRoundups() {
		logger.info("start calculateTransactionRoundups");
		try {
			List<AccountTransaction> unroundedTrxs = accountTrxDao.retrieveUnroundedTrxs();
			BigDecimal nearestDollar = BigDecimal.ZERO;
			for (AccountTransaction unroundedTrx : unroundedTrxs) {
				if (unroundedTrx.getAmount().doubleValue() < 0) {
					nearestDollar = unroundedTrx.getAmount().multiply(new BigDecimal(-1)).setScale(0, RoundingMode.UP);
					unroundedTrx.setRoundupAmount(
							nearestDollar.subtract(unroundedTrx.getAmount().multiply(new BigDecimal(-1))));
				} else {
					nearestDollar = unroundedTrx.getAmount().setScale(0, RoundingMode.UP);
					unroundedTrx.setRoundupAmount(nearestDollar.subtract(unroundedTrx.getAmount()));
				}
				unroundedTrx.setRounded(true);
				BigDecimal cumulativeRoundupsAmount = unroundedTrx.getAccount().getCumulativeRoundupsAmount();
				unroundedTrx.getAccount().setCumulativeRoundupsAmount(cumulativeRoundupsAmount != null
						? cumulativeRoundupsAmount.add(unroundedTrx.getRoundupAmount()) : unroundedTrx.getRoundupAmount());
				accountTrxDao.update(unroundedTrx);
			}
		} catch (Exception e) {
			logger.error("calculateTransactionRoundups "+e.getMessage());
		}
		
	}

	@Scheduled(cron = "00 00 00 * * *")
	@Loggable(logLevel = LogLevel.INFO)
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	public void createPaymentEvent() throws ProcessingException, RepositoryException {
		// TODO create a payment event from source to destination for the week
	}
	
	@Async
//	@Scheduled(cron = "0 0 3 * * ?", zone = "CST")
	@Loggable(logLevel = LogLevel.INFO)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void pullTrxs() throws ProcessingException, ServiceException, RepositoryException {
		StringBuilder faildTxAccount = new StringBuilder("Failed transaction download : <br>");
		boolean isFailed = false;
		try {
			List<NibblerAccount> accts = nibblerAcctDao.findAll();
//			if (!StringUtils.equalsIgnoreCase(env.getActiveProfiles()[0], "prod")) {
//				String active = NibblerDirectoryStatus.ACTIVE.name();
//				accts = accts.stream().filter(a -> {
//					return active.equals(a.getNibbler().getNibblerDir().getStatus());})
//						.collect(Collectors.toList());
//				accts.forEach(nibblerAccount -> {
//					for (int i = 0; i < 5; i++) {
//						faildTxAccount.append("<br>starting add tx test for "+nibblerAccount.getNibbler().getEmail());
//						try {
//							integrationSao.addTestTx(nibblerAccount.getNibbler().getExtAccessToken(),
//									nibblerAccount.getExternalId());
//							logger.info("integrationSao.addTestTx success");
//							faildTxAccount.append("<br>integrationSao.addTestTx success for "+nibblerAccount.getNibbler().getEmail());
//						} catch (Exception e) {
//							logger.info("Exception integrationSao.addTestTx failed==>"+ e.getMessage());
//							faildTxAccount.append("<br> Exception integrationSao.addTestTx failed==>").append(e.getMessage());
//						}
//					}
//				});
//			}
			faildTxAccount.append("<br>accts size "+accts.size());
			Long toDate = System.currentTimeMillis();
			Long fromDate = toDate - 86400000; // 1 day
			
			for (NibblerAccount acct : accts) {
				if (acct.getUseForRounding()) {
//					faildTxAccount.append("<br> account =").append(acct.getNibbler().getEmail()).append(" id =").append(acct.getId()).append(" acct.getUseForRounding() "+acct.getUseForRounding());
					try {
						List<Transaction> extTrxs=new ArrayList<>();
						if (!StringUtils.equalsIgnoreCase(env.getActiveProfiles()[0], "prod")) {
							for(int i=0;i<30;i++){
								Transaction tx=new Transaction();
								tx.setTrxAmount(new BigDecimal("15.01"));
								tx.setDescription("test tx");
								tx.setTrxDate(new Date());
								tx.setTrxId("TX"+new Random().nextInt(1000));
								extTrxs.add(tx);
							}
						}else{
							extTrxs=integrationSao.retrieveTransactions(
									acct.getNibbler().getExtAccessToken(), acct.getExternalId(),
									acct.getLastTransactionPull() == null ? new Date(fromDate)
											: acct.getLastTransactionPull(),
									new Date(toDate), "desc");
						}
//						faildTxAccount.append("<br> ").append(acct.getNibbler().getEmail()).append(" size ").append(extTrxs.size());
						for (Transaction trx : extTrxs) {
//							faildTxAccount.append("<br> Transaction trx : extTrxs :").append(trx);
							AccountTransaction atrx = new AccountTransaction();
							atrx.setAccount(acct);
							atrx.setAmount(trx.getTrxAmount());
							atrx.setDate(trx.getTrxDate());
							atrx.setTransactionId(trx.getTrxId() == null ? trx.getAggregatorTrxId() : trx.getTrxId());
							atrx.setPending(false);

							setCreated(atrx, SYS_USER);

							Location location = new Location();
							location.setAddress(trx.getDescription());
							location.setName(trx.getDescription());

							if (trx.getCategory() != null) {
								TransactionCategory trxCat = trxCatDao.find(trx.getCategory());
								if (trxCat == null) {
									trxCat = new TransactionCategory();
									trxCat.setName(trx.getCategory());
									trxCat.setDescription(trx.getCategory());
									trxCat.getTransactions().add(atrx);
									setCreated(trxCat, SYS_USER);
								}
								atrx.getCategories().add(trxCat);
							}
							atrx.setLocation(location);
							accountTrasactionDao.saveOrUpdate(atrx);
							acct.getTransactions().add(atrx);
//							faildTxAccount.append("acct.getTransactions().add(atrx); "+atrx.getId());
						}
						acct.setLastTransactionPull(new Date(toDate));

						setUpdated(acct, SYS_USER);
						nibblerAcctDao.saveOrUpdate(acct);
//						faildTxAccount.append("nibblerAcctDao.update(acct); "+acct.getId());
					} catch (Exception e) {
						isFailed = true;
						String name = acct.getNibbler().getFirstName() + " " + acct.getNibbler().getLastName();
						if (faildTxAccount.indexOf(name) < 0) {
							faildTxAccount.append("<br>-").append(name).append(" error ").append(e.getMessage());
						}
					}
				}

			}
			if (isFailed) {
				faildTxAccount.append("<br>Best regards,");
				List<String> toEmail = new ArrayList<String>();
				 toEmail.add("tyler@nibbledebt.com");
				 toEmail.add("jalexander.hc.317@gmail.com");
				awsMailSao.sendEmail("Failed transaction download", faildTxAccount.toString(), toEmail);
			}
		} catch (Exception e) {
			throw new ProcessingException("Error while processing transactions.", e);
		}
		calculateTransactionRoundups();
	}

	@Loggable(logLevel = LogLevel.INFO)
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	public void saveTrxs(List<Transaction> trxs) throws ProcessingException, RepositoryException {
		try {
			for (Transaction trx : trxs) {
				NibblerAccount acct = nibblerAcctDao.findByExternalId(trx.getAccountId());
				if (acct != null && acct.getUseForRounding()) {

					AccountTransaction atrx = new AccountTransaction();
					atrx.setAccount(acct);
					atrx.setAmount(trx.getTrxAmount());
					atrx.setDate(trx.getTrxDate());
					atrx.setTransactionId(trx.getTrxId() == null ? trx.getAggregatorTrxId() : trx.getTrxId());
					atrx.setPending(false);

					setCreated(atrx, SYS_USER);

					Location location = new Location();
					location.setAddress(trx.getDescription());
					location.setName(trx.getDescription());

					if (trx.getCategory() != null) {
						TransactionCategory trxCat = trxCatDao.find(trx.getCategory());
						if (trxCat == null) {
							trxCat = new TransactionCategory();
							trxCat.setName(trx.getCategory());
							trxCat.setDescription(trx.getCategory());
							trxCat.getTransactions().add(atrx);
							setCreated(trxCat, SYS_USER);
						}
						atrx.getCategories().add(trxCat);
					}

					atrx.setLocation(location);
					acct.getTransactions().add(atrx);

					acct.setLastTransactionPull(new Date(System.currentTimeMillis()));
					setUpdated(acct, SYS_USER);
					nibblerAcctDao.update(acct);
				}
			}
		} catch (Exception e) {
			throw new ProcessingException("Error while processing transactions.", e);
		}
	}

	@Transactional
	public void removeTestCustomer() throws RepositoryException {
		try {
			Customers customer = integrationSao.getCustomers();
			List<Customer> customers = Arrays.asList(customer.getCustomers());
			customers.forEach(c -> {
				try {
					integrationSao.deleteCustomer(c.getId());
					logger.info("Customer deleted ===>" + c.getId());
				} catch (Exception e) {
					logger.info("error Customer deleted ===>" + c.getId());
					e.printStackTrace();
				}
			});
		} catch (ServiceException e1) {
			logger.info("error loading all customer from finicity");
			e1.printStackTrace();
		}
	}

	private void sendWeeklyTrxSummary(TransactionSummary summary) throws NotificationException {
		try {
			List<String> toEmails = new ArrayList<String>();
			toEmails.add(summary.getEmail());
			toEmails.add("jalexander.hc.317@gmail.com");
			toEmails.add("admin@nibbledebt.com");
			VelocityContext acCtx = new VelocityContext();
			Template acTmpl = velocityEngineFactory.createVelocityEngine().getTemplate("weekly-report.vm");
			StringWriter acWriter = new StringWriter();
			acCtx.put("summary", summary);
			acCtx.put("path", "54.187.155.238:9000");
			acTmpl.merge(acCtx, acWriter);
			awsMailSao.sendEmail("Weekly Reporting", acWriter.toString(), toEmails);
		} catch (Exception e) {
			throw new NotificationException("Error reading template : " + e.getMessage());
		}
	}

	@Scheduled(cron = "0 0 3 * * *")
	@Loggable(logLevel = LogLevel.INFO)
	@Transactional
	public void sendPdf() throws Exception {

		List<Nibbler> nibblers = nibblerDao.findByStatus(NibblerDirectoryStatus.ACTIVE.name());
		List<TransactionSummary> summaries = new ArrayList<>();

		nibblers.forEach(nibbler -> {

			try {
				summaries.add(getWeeklyTrxSummary(nibbler.getNibblerDir().getUsername(),false));
			} catch (Exception e) {
				e.printStackTrace();
			}

		});


		List<String> toEmails = new ArrayList<>();
		toEmails.add("intro85@gmail.com");

		VelocityContext acCtx = new VelocityContext();
		Template acTmpl = velocityEngineFactory.createVelocityEngine().getTemplate("pdf-report.vm");

		StringWriter acWriter = new StringWriter();

		acCtx.put("summaries", summaries);
		acCtx.put("path", "54.187.155.238:9000");
		acTmpl.merge(acCtx, acWriter);
		awsMailSao.sendEmail("Weekly Reporting", acWriter.toString(), toEmails);

		nibblers.forEach(nibbler -> {
			try {
				TransactionSummary summary = getWeeklyTrxSummary(nibbler.getNibblerDir().getUsername(),false);
				if (summary.getSumPayment().compareTo(BigDecimal.valueOf(20.00)) != -1) {
					prepareCheck(nibbler, summary);
				} else {
					below20(nibbler);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

	}

	private void below20(Nibbler nibbler) throws Exception{
		List<String> toEmails = new ArrayList<>();
		toEmails.add(nibbler.getEmail());

		VelocityContext acCtx = new VelocityContext();
		Template acTmpl = velocityEngineFactory.createVelocityEngine().getTemplate("below.vm");

		StringWriter acWriter = new StringWriter();

		acTmpl.merge(acCtx, acWriter);
		awsMailSao.sendEmail("Weekly Reporting", acWriter.toString(), toEmails);
	}

	private void prepareCheck(Nibbler nibbler, TransactionSummary summary) throws Exception {

		List<String> toEmails = new ArrayList<>();
		toEmails.add("intro85@gmail.com");

		VelocityContext acCtx = new VelocityContext();
		Template acTmpl = velocityEngineFactory.createVelocityEngine().getTemplate("checks.vm");

		StringWriter acWriter = new StringWriter();

		acCtx.put("nibbler", nibbler);
		acCtx.put("pay", converteToText(summary.getSumPayment()));
		acCtx.put("summary", summary);
		acTmpl.merge(acCtx, acWriter);
		awsMailSao.sendEmail("Weekly Reporting", acWriter.toString(), toEmails);

	}

	private String converteToText(BigDecimal n) {

		String answer = convert(n.intValue()) + " Dollars and " + convert(n.unscaledValue().intValue()) + "Cents";

		return answer;
	}

	public String convert(final int n) {
		String[] units = {
				"", "one", "two", "three", "four", "five", "six", "seven",
				"eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen",
				"fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
		};

		String[] tens = {
				"",        // 0
				"",        // 1
				"twenty",  // 2
				"thirty",  // 3
				"forty",   // 4
				"fifty",   // 5
				"sixty",   // 6
				"seventy", // 7
				"eighty",  // 8
				"ninety"   // 9
		};

		if (n < 0) {
			return "minus " + convert(-n);
		}

		if (n < 20) {
			return units[n];
		}

		if (n < 100) {
			return tens[n / 10] + ((n % 10 != 0) ? " " : "") + units[n % 10];
		}

		if (n < 1000) {
			return units[n / 100] + " hundred" + ((n % 100 != 0) ? " " : "") + convert(n % 100);
		}

		if (n < 1000000) {
			return convert(n / 1000) + " thousand" + ((n % 1000 != 0) ? " " : "") + convert(n % 1000);
		}

		if (n < 1000000000) {
			return convert(n / 1000000) + " million" + ((n % 1000000 != 0) ? " " : "") + convert(n % 1000000);
		}

		return convert(n / 1000000000) + " billion"  + ((n % 1000000000 != 0) ? " " : "") + convert(n % 1000000000);
	}
}