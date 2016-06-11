/**
 * Copyright 2015-2017. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.processor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

import io.swagger.client.ApiException;

/**
 * @author Rocky Alam
 *
 */
@Component
public class BillingProcessor extends AbstractProcessor {
	@Autowired
	private INibblerDao nibblerDao;

	@Autowired
	@Qualifier("finicitySao")
	private IIntegrationSao integrationSao;

	@Autowired
	private IDwollaClient dwollaClient;

	@Scheduled(cron = "0 0 * * 1 *")
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	public void processPayment() throws RepositoryException {
		List<Nibbler> nibblers = nibblerDao.findByStatus(NibblerDirectoryStatus.ACTIVE.name());
		StringBuilder error = new StringBuilder();
		nibblers.forEach(nibbler -> {
			Map<String, NibblerAccount> sources = new HashMap<String, NibblerAccount>();
			List<String> destinations = new ArrayList<String>();
			Map<NibblerAccount, BigDecimal> payments = new HashMap<>();
			Map<NibblerAccount, BigDecimal> fees = new HashMap<>();
			BigDecimal triggerAmount = nibbler.getNibblerPreferences().getWeeklyTargetAmount();
			nibbler.getAccounts().forEach(account -> {
				if (account.getUseForRounding()) {
					if (account.getCumulativeRoundupsAmount().compareTo(triggerAmount) > 0) {
						sources.put(account.getFundingSourceId(), account);
					}
				} else {
					try {
						if (dwollaClient.getBalance(account.getDwollaLoanId()).compareTo(new BigDecimal("0")) < 0) {
							destinations.add(account.getDwollaLoanId());
						}
					} catch (ApiException e) {
						error.append("Account: ").append(account.getNumber()).append(" error message :")
								.append(e.getMessage());
					}
				}
			});
			sources.forEach((k, v) -> {
				if (destinations.size() > 0) {
					try {
						String destination = destinations.get(0);
						BigDecimal balance = dwollaClient.getBalance(destination);

						if (balance.compareTo(new BigDecimal("0")) < 0) {
							if (balance.compareTo(triggerAmount) > 0) {
								dwollaClient.transfer(v.getFundingSourceId(), destination, triggerAmount.toString(), v);
								v.setCumulativeRoundupsAmount(v.getCumulativeRoundupsAmount().subtract(triggerAmount));
								payments.put(v, triggerAmount);
							} else {
								dwollaClient.transfer(v.getFundingSourceId(), destination, balance.negate().toString(),
										v);
								v.setCumulativeRoundupsAmount(v.getCumulativeRoundupsAmount().subtract(balance));
								payments.put(v, balance);
							}
						} else {
							destinations.remove(0);
						}
						
						if(payFee(nibbler) || v.getBalanceBelow20()>0)
						if (balance.compareTo(new BigDecimal("20")) < 0) {
							if (v.getLastCheckingBalance() != null) {
								int diffDay = Days
										.daysBetween(new DateTime(new Date()), new DateTime(v.getLastCheckingBalance()))
										.getDays();
								if (diffDay == 9) {
									nibbler.getNibblerDir().setStatus(NibblerDirectoryStatus.SUSPEND.name());
									nibbler.getNibblerDir().setLastUpdateStatus(new Date());
									sendErrorFee(null);
								}
							} else {
								v.setLastCheckingBalance(new Date());
								v.setBalanceBelow20(1);
							}
						} else {
							v.setLastCheckingBalance(null);
							v.setBalanceBelow20(0);
							dwollaClient.transfer(v.getFundingSourceId(), v.getNibbler().getNibblerPreferences().getFeeAmount().toString(), v);
							fees.put(v, v.getNibbler().getNibblerPreferences().getFeeAmount());
						}
					} catch (Exception e) {
						error.append("Account: ").append(v.getNumber()).append(" error message :")
								.append(e.getMessage());
					}
				}
			});
			try {
				nibblerDao.update(nibbler);
				transfer(payments,fees);
			} catch (Exception e) {
				error.append("Nibbler: ").append(nibbler.getEmail()).append(" error message :").append(e.getMessage());
			}
		});
		if (error.length() > 0) {
			sendErrorReport(error.toString());
		}
	}

	public void processWeeklyPaymentForUser(Nibbler nibbler) {

	}

	@Notify(notifyMethod = NotifyMethod.EMAIL, notifyType = NotifyType.LOAN_PAYMENT)
	private void transfer(Map<NibblerAccount, BigDecimal> payments,Map<NibblerAccount, BigDecimal> fees) throws ApiException {

	}

	@Notify(notifyMethod = NotifyMethod.EMAIL, notifyType = NotifyType.ERROR_PAYMENT)
	private String sendErrorReport(String error) {
		return error;
	}
	
	@Notify(notifyMethod = NotifyMethod.EMAIL, notifyType = NotifyType.ERROR_FEE)
	private String sendErrorFee(String error) {
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
		return false;
	}
}
