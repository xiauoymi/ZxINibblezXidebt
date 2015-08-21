package com.nibbledebt.intuit.cad.util;

import com.nibbledebt.intuit.cad.exception.AggCatException;
import com.nibbledebt.intuit.cad.exception.OAuthException;

import java.util.Map;

public class OAuthUtil {
	private static final org.slf4j.Logger LOG = Logger.getLogger();
	private String consumerKey;
	private String samlProviderId;
	private String subject;

	public OAuthUtil(String consumerKey, String samlProviderId, String subject) {
		this.consumerKey = consumerKey;
		this.samlProviderId = samlProviderId;
		this.subject = subject;
	}

	public OAuthCredentials getOAuthTokens() throws AggCatException {
		try {
			String oauthSecret = null;
			String oauthToken = null;
			SamlUtil samlUtil = new SamlUtil(this.consumerKey,
					this.samlProviderId, this.subject);
			Map oauthReturns = samlUtil.getSamlResponse(samlUtil
					.createSignedSAMLPayload());
			OAuthCredentials oauthCredentials = new OAuthCredentials();
			oauthSecret = (String) oauthReturns.get("oauth_token_secret");
			oauthToken = (String) oauthReturns.get("oauth_token");
			oauthCredentials.setAccessToken(oauthToken);
			oauthCredentials.setAccessTokenSecret(oauthSecret);
			LOG.debug("Got oauth tokens:" + oauthReturns);
			return oauthCredentials;
		} catch (Exception e) {
			LOG.debug(" Could not get oAuth tokens: " + e.getMessage());
			throw new OAuthException(e.getMessage(), e);
		}
	}

	public String getConsumerKey() {
		return this.consumerKey;
	}
}