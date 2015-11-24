package com.nibbledebt.intuit.cad.service;

import java.io.Serializable;

public class ChallengeSession
  implements Serializable
{
  private static final long serialVersionUID = 3464683734132278753L;
  private String id;
  private String challengeSessionId;
  private String challengeNodeId;
  
  public String getId()
  {
    return this.id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public String getChallengeSessionId()
  {
    return this.challengeSessionId;
  }
  
  public void setChallengeSessionId(String challengeSessionId)
  {
    this.challengeSessionId = challengeSessionId;
  }
  
  public String getChallengeNodeId()
  {
    return this.challengeNodeId;
  }
  
  public void setChallengeNodeId(String challengeNodeId)
  {
    this.challengeNodeId = challengeNodeId;
  }
}
