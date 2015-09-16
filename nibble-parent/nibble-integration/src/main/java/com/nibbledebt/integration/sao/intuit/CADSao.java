/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.sao.intuit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nibbledebt.common.error.ServiceException;
import com.nibbledebt.integration.model.cad.AccountList;
import com.nibbledebt.integration.model.cad.Address;
import com.nibbledebt.integration.model.cad.BankingAccount;
import com.nibbledebt.integration.model.cad.Challenges;
import com.nibbledebt.integration.model.cad.CreditAccount;
import com.nibbledebt.integration.model.cad.Institution;
import com.nibbledebt.integration.model.cad.InstitutionDetail;
import com.nibbledebt.integration.model.cad.Key;
import com.nibbledebt.integration.model.cad.Keys;
import com.nibbledebt.integration.model.cad.LinkResponse;
import com.nibbledebt.integration.model.cad.LoanAccount;
import com.nibbledebt.intuit.cad.data.Credential;
import com.nibbledebt.intuit.cad.data.Credentials;
import com.nibbledebt.intuit.cad.data.InstitutionLogin;
import com.nibbledebt.intuit.cad.exception.AggCatException;
import com.nibbledebt.intuit.cad.service.AggCatServiceFactory;
import com.nibbledebt.intuit.cad.service.DiscoverAndAddAccountsResponse;

/**
 * @author ralam
 *
 */
@Component("cadSao")
public class CADSao implements IIntuitSao{
	@Autowired
	private Mapper mapper;

	@Value("${intuit.consumer.key}")
	private String consumerKey;
	
	@Value("${intuit.consumer.secret}")
	private String consumerSecret;
	
	@Value("${intuit.saml.id}")
	private String samlId;
		
	
	@Override
	public LinkResponse discoverAndAddAccounts(String institutionId, Map<String,String> loginCreds, String userId) throws ServiceException {
		try {
			InstitutionLogin instLogin = new InstitutionLogin();
			Credentials creds = new Credentials();
			for(String credKey : loginCreds.keySet()){
				Credential cred = new Credential();
				cred.setName(credKey);
				cred.setValue(loginCreds.get(credKey));
				creds.getCredentials().add(cred);
			}
			instLogin.setCredentials(creds);
			DiscoverAndAddAccountsResponse resp = (AggCatServiceFactory.getService(consumerKey, consumerSecret, samlId, userId))
														.discoverAndAddAccounts(Long.valueOf(institutionId), instLogin, "json");

			LinkResponse linkResponse = new LinkResponse();
			
			if(resp.getChallenges() != null && resp.getChallenges().getChallenges()!=null  
											&&	!resp.getChallenges().getChallenges().isEmpty()){
				Challenges challenges = new Challenges();
				for(com.nibbledebt.intuit.cad.data.Challenges.Challenge ch : resp.getChallenges().getChallenges()){
					challenges.getChallenges().add(mapper.map(ch, Challenges.Challenge.class));
				}
				linkResponse.setChallenges(challenges);
			}
			
			if(resp.getAccountList() != null && resp.getAccountList().getAccounts()!=null  
											&&	!resp.getAccountList().getAccounts().isEmpty()){
				AccountList accountslist = new AccountList();
				for(com.nibbledebt.intuit.cad.data.Account acct : resp.getAccountList().getAccounts()){
					if(acct instanceof com.nibbledebt.intuit.cad.data.BankingAccount)
						accountslist.getBankingAccountsAndCreditAccountsAndLoanAccounts().add(mapper.map(acct, BankingAccount.class));
					else if(acct instanceof com.nibbledebt.intuit.cad.data.LoanAccount)
						accountslist.getBankingAccountsAndCreditAccountsAndLoanAccounts().add(mapper.map(acct, LoanAccount.class));
					else if(acct instanceof com.nibbledebt.intuit.cad.data.CreditAccount)
						accountslist.getBankingAccountsAndCreditAccountsAndLoanAccounts().add(mapper.map(acct, CreditAccount.class));
					
				}
				linkResponse.setAccountList(accountslist);
			}
			return linkResponse;
		} catch (AggCatException e) {
			throw new ServiceException("Error while linking with the supported institution in Intuit CAD.", e);
		}
	}
	
	@Override
	public void deleteCustomers() throws ServiceException{
		try {
			(AggCatServiceFactory.getService(consumerKey, consumerSecret, samlId, "system")).deleteCustomer("json");
		} catch (AggCatException e) {
			throw new ServiceException("Error while deleting all customers", e);
		}
	}

	@Override
	public List<Institution> getInstitutions() throws ServiceException {
		List<Institution> institutions = new ArrayList<>();
		try {
			com.nibbledebt.intuit.cad.data.Institutions intuitInsts = (AggCatServiceFactory.getService(consumerKey, consumerSecret, samlId, "system")).getInstitutions("json");
			for(com.nibbledebt.intuit.cad.data.Institution intuitInst : intuitInsts.getInstitutions()){
				if(!intuitInst.isVirtual()){
					Institution inst = new Institution();
					inst.setInstitutionId(intuitInst.getInstitutionId());
					inst.setInstitutionName(intuitInst.getInstitutionName());
					inst.setHomeUrl(intuitInst.getHomeUrl());
					inst.setPhoneNumber(intuitInst.getPhoneNumber());
					institutions.add(inst);
				}
			}
			return institutions;
		} catch (AggCatException e) {
			throw new ServiceException("Error while retrieving the supported institutions from Intuit CAD.", e);
		}
	}

	@Override
	public InstitutionDetail getInstitution(String institutionId) throws ServiceException {
		InstitutionDetail instDetail = null;
		try {
			com.nibbledebt.intuit.cad.data.InstitutionDetail cadInstDetail = (AggCatServiceFactory.getService(consumerKey, consumerSecret, samlId, "system")).getInstitutionDetails(Long.valueOf(institutionId), "json");
			if(cadInstDetail!=null){
				instDetail = new InstitutionDetail();
				instDetail.setInstitutionId(cadInstDetail.getInstitutionId());
				instDetail.setCurrencyCode(cadInstDetail.getCurrencyCode().value());
				instDetail.setInstitutionName(cadInstDetail.getInstitutionName());
				instDetail.setSpecialText(cadInstDetail.getSpecialText());
				instDetail.setHomeUrl(cadInstDetail.getHomeUrl());
				if(cadInstDetail.getAddress()!=null){
					instDetail.setAddress(mapper.map(cadInstDetail.getAddress(), Address.class));
				}
				Keys keys = new Keys();
				for(com.nibbledebt.intuit.cad.data.InstitutionDetail.Keys.Key key : cadInstDetail.getKeys().getKeies()){
					keys.getKey().add(mapper.map(key, Key.class));
				}
				instDetail.setKeys(keys);
			}
			return instDetail;
		} catch (AggCatException e) {
			throw new ServiceException("Error while retrieving institution details from Intuit CAD.", e);
		}
	}
}