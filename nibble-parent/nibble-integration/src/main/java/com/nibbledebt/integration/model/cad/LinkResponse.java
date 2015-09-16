/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.model.cad;

/**
 * @author ralam
 *
 */
public class LinkResponse {
	private AccountList accountList;
	private Challenges challenges;
	private ChallengeSession challengeSession;
	/**
	 * @return the accountList
	 */
	public AccountList getAccountList() {
		return accountList;
	}
	/**
	 * @param accountList the accountList to set
	 */
	public void setAccountList(AccountList accountList) {
		this.accountList = accountList;
	}
	/**
	 * @return the challenges
	 */
	public Challenges getChallenges() {
		return challenges;
	}
	/**
	 * @param challenges the challenges to set
	 */
	public void setChallenges(Challenges challenges) {
		this.challenges = challenges;
	}
	/**
	 * @return the challengeSession
	 */
	public ChallengeSession getChallengeSession() {
		return challengeSession;
	}
	/**
	 * @param challengeSession the challengeSession to set
	 */
	public void setChallengeSession(ChallengeSession challengeSession) {
		this.challengeSession = challengeSession;
	}
	
}
