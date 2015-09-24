package com.nibbledebt.intuit.cad.service;

import java.io.Serializable;

import com.nibbledebt.intuit.cad.data.Challenges;

public class UpdateInstitutionLoginResponse
  implements Serializable
{
  private static final long serialVersionUID = 151920594490967457L;
  private boolean isUpdated = false;
  private Challenges challenges;
  private ChallengeSession challengeSession;

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

  public boolean isUpdated()
  {
    return this.isUpdated;
  }

  public void setUpdated(boolean isUpdated)
  {
    this.isUpdated = isUpdated;
  }
}