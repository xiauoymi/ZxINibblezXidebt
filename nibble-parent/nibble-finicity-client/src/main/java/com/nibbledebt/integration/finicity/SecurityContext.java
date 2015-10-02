/**
 * 
 */
package com.nibbledebt.integration.finicity;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.nibbledebt.integration.finicity.error.PartnerAuthenticationException;
import com.nibbledebt.integration.finicity.model.Access;
import com.nibbledebt.integration.finicity.model.Credentials;

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
	
	@Value("${finicity.auth.url}")
	private String finicityAuthUrl;	
	
	@Value("${finicity.partner.id}")
	private String partnerId;
	
	@Value("${finicity.partner.secret}")
	private String partnerSecret;
	
	@Value("${finicity.app.key}")
	private String appKey;
		
	private String appToken;
	private Date lastPullTime;
	
	/**
	 * @return the partnerId
	 */
	public String getPartnerId() {
		return partnerId;
	}
	/**
	 * @param partnerId the partnerId to set
	 */
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	/**
	 * @return the partnerSecret
	 */
	public String getPartnerSecret() {
		return partnerSecret;
	}
	/**
	 * @param partnerSecret the partnerSecret to set
	 */
	public void setPartnerSecret(String partnerSecret) {
		this.partnerSecret = partnerSecret;
	}
	/**
	 * @return the appToken
	 */
	public String getAppToken() throws PartnerAuthenticationException{
		refreshToken();
		return appToken;
	}
	
	/**
	 * Refreshes the security token from finicity
	 */
	public void refreshToken() throws PartnerAuthenticationException{
		if(this.appToken == null){
			this.appToken = invokeAuthService().getToken();
			this.lastPullTime = new Date();
		}else{
			if(this.lastPullTime!=null && this.lastPullTime.getTime() < System.currentTimeMillis()+720000){
				this.appToken = invokeAuthService().getToken();
				this.lastPullTime = new Date();
			}
		}
		headerInterceptor.setToken(this.appToken);		
	}
	
	/**
	 * @param appToken the appToken to set
	 */
	public void setAppToken(String appToken) {
		this.appToken = appToken;
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
		
}