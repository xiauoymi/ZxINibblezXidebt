package com.nibbledebt.intuit.cad.service;

import java.io.Serializable;

import com.nibbledebt.intuit.cad.data.AccountList;
import com.nibbledebt.intuit.cad.data.Challenges;

public class DiscoverAndAddAccountsResponse
  implements Serializable
{
  private static final long serialVersionUID = -4076404639040390219L;
  private AccountList accountList;
  private Challenges challenges;
  private ChallengeSession challengeSession;
  
  public AccountList getAccountList()
  {
    return this.accountList;
  }
  
  public void setAccountList(AccountList accountList)
  {
    this.accountList = accountList;
  }
  
  public Challenges getChallenges()
  {
    return this.challenges;
  }
  
  public void setChallenges(Challenges challenges)
  {
    this.challenges = challenges;
  }
  
  public ChallengeSession getChallengeSession()
  {
    return this.challengeSession;
  }
  
  public void setChallengeSession(ChallengeSession challengeSession)
  {
    this.challengeSession = challengeSession;
  }
}
