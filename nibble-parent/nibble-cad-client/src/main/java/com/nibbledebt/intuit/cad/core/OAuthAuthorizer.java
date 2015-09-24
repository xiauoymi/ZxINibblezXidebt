package com.nibbledebt.intuit.cad.core;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.signature.AuthorizationHeaderSigningStrategy;

import org.apache.http.client.methods.HttpRequestBase;

import com.nibbledebt.intuit.cad.exception.AggCatException;
import com.nibbledebt.intuit.cad.util.OAuthCredentials;
import com.nibbledebt.intuit.cad.util.OAuthUtil;
import com.nibbledebt.intuit.cad.util.StringUtils;

public class OAuthAuthorizer
  implements IAuthorizer
{
  private OAuthConsumer oAuthConsumer;

  public void authorize(HttpRequestBase httpRequest)
    throws AggCatException
  {
    try
    {
      this.oAuthConsumer.sign(httpRequest);
    } catch (Exception e) {
      throw new AggCatException(e);
    }
  }

  private OAuthAuthorizer(String consumerKey, String consumerSecret)
  {
    this.oAuthConsumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
  }

  public OAuthAuthorizer(String consumerKey, String consumerSecret, OAuthCredentials oAuthCredentials)
  {
    this(consumerKey, consumerSecret);
    this.oAuthConsumer.setTokenWithSecret(oAuthCredentials.getAccessToken(), oAuthCredentials.getAccessTokenSecret());
    this.oAuthConsumer.setSigningStrategy(new AuthorizationHeaderSigningStrategy());
  }

  public OAuthAuthorizer(String consumerKey, String consumerSecret, String samlProviderId, String subject)
    throws AggCatException
  {
    this(consumerKey, consumerSecret);

    if (!StringUtils.hasText(samlProviderId)) {
      throw new AggCatException("SAML provider Id is null or empty");
    }

    OAuthUtil oauthUtil = new OAuthUtil(consumerKey, samlProviderId, subject);
    OAuthCredentials oauthCredentials = oauthUtil.getOAuthTokens();
    if (null != oauthCredentials) {
      this.oAuthConsumer.setTokenWithSecret(oauthCredentials.getAccessToken(), oauthCredentials.getAccessTokenSecret());
      this.oAuthConsumer.setSigningStrategy(new AuthorizationHeaderSigningStrategy());
    }
  }
}