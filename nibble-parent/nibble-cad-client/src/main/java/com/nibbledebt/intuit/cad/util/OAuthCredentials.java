package com.nibbledebt.intuit.cad.util;

public class OAuthCredentials
{
  private String accessToken;
  private String accessTokenSecret;

  public String getAccessToken()
  {
    return this.accessToken;
  }

  public void setAccessToken(String accessToken)
  {
    this.accessToken = accessToken;
  }

  public String getAccessTokenSecret()
  {
    return this.accessTokenSecret;
  }

  public void setAccessTokenSecret(String accessTokenSecret)
  {
    this.accessTokenSecret = accessTokenSecret;
  }
}