/**
 * 
 */
package com.nibbledebt.core.processor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
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
	
	@Transactional(readOnly=true)
	public TransactionSummary getWeeklyTrxSummary() throws ProcessingException, RepositoryException{
		TransactionSummary summary = new TransactionSummary();
		Nibbler nibbler = nibblerDao.find(getCurrentUser());
		BigDecimal target = nibbler.getNibblerPreferences().getWeeklyTargetAmount();
		
		BigDecimal weeklyTotal = BigDecimal.ZERO;
		LocalDate now = new LocalDate();

		List<com.nibbledebt.domain.model.Transaction> wtrxs = new ArrayList<>();
		for(NibblerAccount nacct : nibbler.getAccounts()){
			if(nacct.getUseForRounding()){
				for(int i=7; i>0; i--){
					BigDecimal dailyTotal = BigDecimal.ZERO;
					Date day = new Date(now.withDayOfWeek(DateTimeConstants.SATURDAY).minusDays(i).toDate().getTime());
					List<AccountTransaction> trxsForDay = accountTrxDao.retrieveTrxs(nacct.getId(), day, day);
					for(AccountTransaction trx : trxsForDay){
						com.nibbledebt.domain.model.Transaction dtrx = new com.nibbledebt.domain.model.Transaction();
						dtrx.setCity(trx.getLocation().getCity());
						dtrx.setRoundupAmount(trx.getRoundupAmount());
						dtrx.setState(trx.getLocation().getState());
						dtrx.setTrxAmount(trx.getAmount());
						dtrx.setTrxDate(trx.getDate());
						dtrx.setTrxId(trx.getTransactionId());
						dtrx.setAccountNumber(trx.getAccount().getNumberMask());
						dtrx.setInstitutionName(trx.getAccount().getInstitution().getName());
						dailyTotal = dailyTotal.add(dtrx.getRoundupAmount());
						weeklyTotal = weeklyTotal.add(dtrx.getRoundupAmount());
						wtrxs.add(dtrx);
					}
					if(i==6) summary.setDay0total(summary.getDay0total().add(dailyTotal));
					else if(i==5) summary.setDay1total(summary.getDay1total().add(dailyTotal));
					else if(i==4) summary.setDay2total(summary.getDay2total().add(dailyTotal));
					else if(i==3) summary.setDay3total(summary.getDay3total().add(dailyTotal));
					else if(i==2) summary.setDay4total(summary.getDay4total().add(dailyTotal));
					else if(i==1) summary.setDay5total(summary.getDay5total().add(dailyTotal));
					else summary.setDay6total(summary.getDay6total().add(dailyTotal));
				}
			}
		}
		
		
		
		
		summary.setCurrentWeekAmount(weeklyTotal);
		summary.setCurrentTargetPercent(weeklyTotal.divide(target, 2, RoundingMode.UP).multiply(new BigDecimal("100")).intValue());
		summary.setWeeklyTarget(target);
		summary.setTrxs(wtrxs);
		return summary;
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, isolation=Isolation.READ_COMMITTED)
	public List<com.nibbledebt.domain.model.Transaction> retrieveTransactions(Long accountId) throws ProcessingException, RepositoryException{
		List<AccountTransaction> transactions = accountTrxDao.retrieveTrxs(accountId);
		List<com.nibbledebt.domain.model.Transaction> wtrxs = new ArrayList<>();
		for(AccountTransaction trx : transactions){
			com.nibbledebt.domain.model.Transaction wtrx = new com.nibbledebt.domain.model.Transaction();
			wtrx.setCity(trx.getLocation().getCity());
			wtrx.setRoundupAmount(trx.getRoundupAmount());
			wtrx.setState(trx.getLocation().getState());
			wtrx.setTrxAmount(trx.getAmount());
			wtrx.setTrxDate(trx.getDate());
			wtrx.setTrxId(trx.getTransactionId());
			wtrxs.add(wtrx);
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
			
			accountTrxDao.update(unroundedTrx);
		}
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
	public void saveTrxs(List<Transaction> trxs, String customerId, String accountId) throws ProcessingException, ServiceException, RepositoryException{
		try {
			NibblerAccount acct = nibblerAcctDao.findByExternalId(accountId);	

			Long toDate = System.currentTimeMillis();
			Long fromDate = toDate-5184000000l; //60 days
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
		} catch (Exception e) {
			throw new ProcessingException("Error while processing transactions.", e);
		}
	}
}
