/**
 * 
 */
package com.nibbledebt.integration.intuitcad;

import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.nibbledebt.integration.intuitcad.error.PartnerAuthenticationException;
import com.nibbledebt.intuit.cad.data.Credentials;
import com.nibbledebt.intuit.cad.exception.SamlAssertionException;
import com.nibbledebt.intuit.cad.util.Config;
import com.nibbledebt.intuit.cad.util.SAML2AssertionGenerator;
import com.nibbledebt.intuit.cad.util.SAMLAssertionData;
import com.nibbledebt.intuit.cad.util.SAMLCredentials;
import com.nibbledebt.intuit.cad.util.StringUtils;

/**
 * @author alam_home
 *
 */
@Component
public class SecurityContext {		
	@Autowired
	private RestClient restClient;
	
	@Autowired
	private HeaderInterceptor headerInterceptor;
	
	@Value("${intuit.consumer.key}")
	private String consumerKey;	
	
	@Value("${intuit.consumer.secret}")
	private String consumerSecret;
	
	@Value("${intuit.saml.id}")
	private String samlId;
		
	private String accessToken;
	private String accessTokenSecret;
	
	private Date lastPullTime;

	/**
	 * @return the restClient
	 */
	public RestClient getRestClient() {
		return restClient;
	}

	/**
	 * @param restClient the restClient to set
	 */
	public void setRestClient(RestClient restClient) {
		this.restClient = restClient;
	}

	/**
	 * @return the headerInterceptor
	 */
	public HeaderInterceptor getHeaderInterceptor() {
		return headerInterceptor;
	}

	/**
	 * @param headerInterceptor the headerInterceptor to set
	 */
	public void setHeaderInterceptor(HeaderInterceptor headerInterceptor) {
		this.headerInterceptor = headerInterceptor;
	}

	/**
	 * @return the consumerKey
	 */
	public String getConsumerKey() {
		return consumerKey;
	}

	/**
	 * @param consumerKey the consumerKey to set
	 */
	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	/**
	 * @return the consumerSecret
	 */
	public String getConsumerSecret() {
		return consumerSecret;
	}

	/**
	 * @param consumerSecret the consumerSecret to set
	 */
	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}

	/**
	 * @return the samlId
	 */
	public String getSamlId() {
		return samlId;
	}

	/**
	 * @param samlId the samlId to set
	 */
	public void setSamlId(String samlId) {
		this.samlId = samlId;
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * @return the accessTokenSecret
	 */
	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}

	/**
	 * @param accessTokenSecret the accessTokenSecret to set
	 */
	public void setAccessTokenSecret(String accessTokenSecret) {
		this.accessTokenSecret = accessTokenSecret;
	}

	/**
	 * @return the lastPullTime
	 */
	public Date getLastPullTime() {
		return lastPullTime;
	}
	/**
	 * @param lastPullTime the lastPullTime to set
	 */
	public void setLastPullTime(Date lastPullTime) {
		this.lastPullTime = lastPullTime;
	}


	/**
	 * Refreshes the security token from finicity
	 */
	public void refreshToken() throws PartnerAuthenticationException{
		if(this.appToken == null){
			this.appToken = invokeAuthService().getToken();
			this.lastPullTime = new Date();
		}else{
			if(this.lastPullTime!=null && this.lastPullTime.getTime() < System.currentTimeMillis()+360000){
				this.appToken = invokeAuthService().getToken();
				this.lastPullTime = new Date();
			}
		}
		headerInterceptor.setToken(this.appToken);		
	}
	
	/**
	 * Reusable method for invokiung the auth service of Finicity
	 * @return
	 * @throws PartnerAuthenticationException
	 */
	protected Access invokeAuthService() throws PartnerAuthenticationException{
		try {
			Credentials credentials = new Credentials();
			credentials.setPartnerId(partnerId);
			credentials.setPartnerSecret(partnerSecret);
			ResponseEntity<Access> resp = restClient.postForEntity(finicityAuthUrl, credentials, Access.class);
			return resp.getBody();
		} catch (Exception e) {
			throw new PartnerAuthenticationException(e);
		}
	}
	
	public String createSignedSAMLPayload() throws SamlAssertionException {
		SAMLAssertionData samlData = new SAMLAssertionData(this.subject,
				this.samlProviderId, this.samlProviderId);
		SAML2AssertionGenerator gen = new SAML2AssertionGenerator();
		String samlStr = null;
		try {
			SAMLCredentials cred = new SAMLCredentials(
					Config.getProperty("saml.keystoreFile"),
					Config.getProperty("saml.keystorePassword"),
					Config.getProperty("saml.certAlias"),
					Config.getProperty("saml. "));

			samlStr = gen.generateSignedAssertion(samlData, cred);
		} catch (Exception e) {
			throw new SamlAssertionException(e);
		}

		return encodeBase64UrlSaml(samlStr);
	}

	public String encodeBase64UrlSaml(String samlStr) {
		if (StringUtils.hasText(samlStr)) {
			return new String(Base64.encodeBase64URLSafeString(samlStr
					.getBytes()));
		}
		return null;
	}
		
}