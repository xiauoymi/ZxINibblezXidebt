/**
 * 
 */
package com.nibbledebt.core.processor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nibbledebt.common.error.ProcessingException;
import com.nibbledebt.common.error.ServiceException;
import com.nibbledebt.common.logging.LogLevel;
import com.nibbledebt.common.logging.Loggable;
import com.nibbledebt.core.data.dao.IAccountTransactionDao;
import com.nibbledebt.core.data.dao.INibblerAccountDao;
import com.nibbledebt.core.data.dao.INibblerDao;
import com.nibbledebt.core.data.dao.ITransactionCategoryDao;
import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.AccountTransaction;
import com.nibbledebt.core.data.model.Location;
import com.nibbledebt.core.data.model.Nibbler;
import com.nibbledebt.core.data.model.NibblerAccount;
import com.nibbledebt.core.data.model.NibblerType;
import com.nibbledebt.core.data.model.TransactionCategory;
import com.nibbledebt.domain.model.Transaction;
import com.nibbledebt.domain.model.TransactionSummary;
import com.nibbledebt.integration.sao.IIntegrationSao;

/**
 * @author Rocky Alam
 *
 */
@Component
public class TransactionProcessor extends AbstractProcessor{
	private static final String SYS_USER = "sys_user";
	
	@Autowired
	private INibblerDao nibblerDao;
	
	@Autowired
	private IAccountTransactionDao accountTrxDao;
	
	@Autowired
	private INibblerAccountDao nibblerAcctDao;
	
	
	@Autowired
	@Qualifier("finicitySao")
	private IIntegrationSao integrationSao;
	
	@Autowired
	private ITransactionCategoryDao trxCatDao;
	public static void main(String[] args){
		System.out.println(System.currentTimeMillis()/1000);
	}
	
	@Transactional(readOnly=true)
	public TransactionSummary getWeeklyTrxSummary(String username) throws ProcessingException, RepositoryException{
		TransactionSummary summary = new TransactionSummary();
		Nibbler nibbler = nibblerDao.find(username);
		summary.setPersonFirstName(nibbler.getFirstName());
		summary.setPersonLastName(nibbler.getLastName());
		summary.setPersonId(nibbler.getId());
		
		LocalDate now = LocalDate.now();
		if(StringUtils.equalsIgnoreCase(nibbler.getType(), NibblerType.receiver.name())){
			calculateSummary(summary, nibbler, now, false);
			List<Nibbler> contributors = nibblerDao.findContributors(nibbler.getId());
			for(Nibbler contributor : contributors ) calculateSummary(summary, contributor, now, true);
		}else{
			calculateSummary(summary, nibbler, now, false);
		}
		return summary;
	}
	
	private TransactionSummary calculateSummary(TransactionSummary summary, Nibbler nibbler, LocalDate now, Boolean asContributor) throws RepositoryException{
		List<com.nibbledebt.domain.model.Transaction> wtrxs = new ArrayList<>();
		BigDecimal weeklyTotal = BigDecimal.ZERO;
		BigDecimal target = nibbler.getNibblerPreferences().getWeeklyTargetAmount();
		TransactionSummary contributorSummary = null;
		for(NibblerAccount nacct : nibbler.getAccounts()){
			if(nacct.getUseForRounding()){
				for(int i=1; i<8; i++){
					BigDecimal dailyTotal = BigDecimal.ZERO;
					TemporalField fieldUS = WeekFields.of(Locale.US).dayOfWeek();
					Date dayStart = Date.from(Instant.from((now.with(fieldUS, i).atStartOfDay().atZone(ZoneId.systemDefault()))));
					Date dayEnd = Date.from(Instant.from((now.with(fieldUS, i).atStartOfDay().atZone(ZoneId.systemDefault()))).plusMillis(86399999));
					List<AccountTransaction> trxsForDay = accountTrxDao.retrieveTrxs(nacct.getId(), dayStart, dayEnd);
					for(AccountTransaction trx : trxsForDay){
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
						dailyTotal = dailyTotal.add(dtrx.getRoundupAmount());
						weeklyTotal = weeklyTotal.add(dtrx.getRoundupAmount());
						wtrxs.add(dtrx);
					}
					if(!asContributor){
						if(i==1) summary.setDay0total(summary.getDay0total().add(dailyTotal));
						else if(i==2) summary.setDay1total(summary.getDay1total().add(dailyTotal));
						else if(i==3) summary.setDay2total(summary.getDay2total().add(dailyTotal));
						else if(i==4) summary.setDay3total(summary.getDay3total().add(dailyTotal));
						else if(i==5) summary.setDay4total(summary.getDay4total().add(dailyTotal));
						else if(i==6) summary.setDay5total(summary.getDay5total().add(dailyTotal));
						else if(i==7) summary.setDay6total(summary.getDay6total().add(dailyTotal));					
					}else{
						if(contributorSummary == null) contributorSummary = new TransactionSummary();
						if(i==1) contributorSummary.setDay0total(contributorSummary.getDay0total().add(dailyTotal));
						else if(i==2) contributorSummary.setDay1total(contributorSummary.getDay1total().add(dailyTotal));
						else if(i==3) contributorSummary.setDay2total(contributorSummary.getDay2total().add(dailyTotal));
						else if(i==4) contributorSummary.setDay3total(contributorSummary.getDay3total().add(dailyTotal));
						else if(i==5) contributorSummary.setDay4total(contributorSummary.getDay4total().add(dailyTotal));
						else if(i==6) contributorSummary.setDay5total(contributorSummary.getDay5total().add(dailyTotal));
						else if(i==7) contributorSummary.setDay6total(contributorSummary.getDay6total().add(dailyTotal));
						
					}
				}
				if(!asContributor){

					summary.setTotalAmountPaid(summary.getTotalAmountPaid().add((nacct.getCumulativeRoundupsAmount()==null ? BigDecimal.ZERO : nacct.getCumulativeRoundupsAmount())));
				}else{
					if(contributorSummary != null)
						contributorSummary.setTotalAmountPaid(contributorSummary.getTotalAmountPaid().add((nacct.getCumulativeRoundupsAmount()==null ? BigDecimal.ZERO : nacct.getCumulativeRoundupsAmount())));
					
				}
				
			}
		}
		if(!asContributor){
			summary.setCurrentWeekAmount(weeklyTotal);
			summary.setCurrentTargetPercent(weeklyTotal.divide(target, 2, RoundingMode.UP).multiply(new BigDecimal("100")).intValue());
			summary.setWeeklyTarget(target);
			summary.setTrxs(wtrxs);
		}else{
			if(contributorSummary != null){
				contributorSummary.setCurrentWeekAmount(weeklyTotal);
				contributorSummary.setCurrentTargetPercent(weeklyTotal.divide(target, 2, RoundingMode.UP).multiply(new BigDecimal("100")).intValue());
				contributorSummary.setWeeklyTarget(target);
				contributorSummary.setTrxs(wtrxs);
				contributorSummary.setPersonFirstName(nibbler.getFirstName());
				contributorSummary.setPersonLastName(nibbler.getLastName());
				contributorSummary.setPersonId(nibbler.getId());
				summary.getContributorSummaries().add(contributorSummary);
			}
		}
		return summary;
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, isolation=Isolation.READ_COMMITTED)
	public List<com.nibbledebt.domain.model.Transaction> retrieveTransactions(Long accountId, Long sinceDays) throws ProcessingException, RepositoryException{
		List<AccountTransaction> transactions;
		if(sinceDays > 0){
//			LocalTime midnight = LocalTime.MIDNIGHT;
//			LocalDate today = LocalDate.now(ZoneId.systemDefault());
					
			Date to = Date.from(Instant.now());
			Date from = Date.from(Instant.now().minus(sinceDays, ChronoUnit.DAYS));
			transactions = accountTrxDao.retrieveTrxs(accountId, from, to);
		}else{
			
			transactions = accountTrxDao.retrieveTrxs(accountId);
		}
		
		List<com.nibbledebt.domain.model.Transaction> wtrxs = new ArrayList<>();
		for(AccountTransaction trx : transactions){
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
	
	@Scheduled(fixedDelay=60000)
	@Loggable(logLevel=LogLevel.INFO)
	@Transactional(propagation=Propagation.REQUIRES_NEW, isolation=Isolation.READ_COMMITTED)
	public void calculateTransactionRoundups() throws ProcessingException, RepositoryException{
		List<AccountTransaction> unroundedTrxs = accountTrxDao.retrieveUnroundedTrxs();
		BigDecimal nearestDollar = BigDecimal.ZERO;
		for(AccountTransaction unroundedTrx : unroundedTrxs){
			if(unroundedTrx.getAmount().doubleValue() < 0){
				nearestDollar = unroundedTrx.getAmount().multiply(new BigDecimal(-1)).setScale(0, RoundingMode.UP);
				unroundedTrx.setRoundupAmount(nearestDollar.subtract(unroundedTrx.getAmount().multiply(new BigDecimal(-1))));
			}else{
				nearestDollar = unroundedTrx.getAmount().setScale(0, RoundingMode.UP);
				unroundedTrx.setRoundupAmount(nearestDollar.subtract(unroundedTrx.getAmount()));
			}
						
			unroundedTrx.setRounded(true);
			unroundedTrx.getAccount().setCumulativeRoundupsAmount(unroundedTrx.getAccount().getCumulativeRoundupsAmount()!=null ? unroundedTrx.getAccount().getCumulativeRoundupsAmount().add(unroundedTrx.getRoundupAmount()) : unroundedTrx.getRoundupAmount());
			accountTrxDao.update(unroundedTrx);
		}
	}
	
	@Scheduled(cron="00 00 00 * * *")
	@Loggable(logLevel=LogLevel.INFO)
	@Transactional(propagation=Propagation.REQUIRES_NEW, isolation=Isolation.READ_COMMITTED)
	public void createPaymentEvent() throws ProcessingException, RepositoryException{
		//TODO create a payment event from source to destination for the week
	}
	
//	@Scheduled(fixedDelay=60000)
	@Loggable(logLevel=LogLevel.INFO)
	@Transactional(propagation=Propagation.REQUIRES_NEW, isolation=Isolation.READ_COMMITTED)
	public void pullTrxs() throws ProcessingException, ServiceException, RepositoryException{
		try {
			List<NibblerAccount> accts = nibblerAcctDao.findAll();	

			Long toDate = System.currentTimeMillis();
			Long fromDate = toDate-5184000000l; //60 days
			for(NibblerAccount acct : accts){
				if(acct.getUseForRounding()){
					List<Transaction> extTrxs = integrationSao.retrieveTransactions(acct.getNibbler().getExtAccessToken(), acct.getExternalId(), acct.getLastTransactionPull()==null ? new Date(fromDate) : acct.getLastTransactionPull(), new Date(toDate), "desc");
					for(Transaction trx : extTrxs){
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
						
						if(trx.getCategory() != null){
							TransactionCategory trxCat = trxCatDao.find(trx.getCategory());
							if(trxCat == null){
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
					}
					acct.setLastTransactionPull(new Date((toDate/1000)*1000));

					setUpdated(acct, SYS_USER);
					nibblerAcctDao.update(acct);
				}
				
			}
		} catch (Exception e) {
			throw new ProcessingException("Error while processing transactions.", e);
		}
	}
	
	@Loggable(logLevel=LogLevel.INFO)
	@Transactional(propagation=Propagation.REQUIRES_NEW, isolation=Isolation.READ_COMMITTED)
	public void saveTrxs(List<Transaction> trxs) throws ProcessingException, RepositoryException{
		try {
			for(Transaction trx : trxs){
				NibblerAccount acct = nibblerAcctDao.findByExternalId(trx.getAccountId());	
				if(acct!=null && acct.getUseForRounding()){
					
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
					
					if(trx.getCategory() != null){
						TransactionCategory trxCat = trxCatDao.find(trx.getCategory());
						if(trxCat == null){
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
	
}