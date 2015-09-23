/**
 * 
 */
package com.nibbledebt.integration.finicity;

import java.security.Timestamp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.stereotype.Component;

import com.nibbledebt.integration.finicity.error.PartnerAuthenticationException;
import com.nibbledebt.integration.finicity.model.Access;
import com.nibbledebt.integration.finicity.model.Credentials;

/**
 * @author alam_home
 *
 */
@Component
public class SecurityContext implements ApplicationContextAware{
	private ApplicationContext applicationContext;
		
	@Value("${finicity.auth.url}")
	private String finicityAuthUrl;	
	
	@Value("${finicity.partner.id}")
	private String partnerId;
	
	@Value("${finicity.partner.secret}")
	private String partnerSecret;
	
	@Value("${finicity.app.key}")
	private String appKey;
	
	private String appToken;
	private Timestamp lastPullTime;
	
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
	 * @return the appKey
	 */
	public String getAppKey() {
		return appKey;
	}
	/**
	 * @param appKey the appKey to set
	 */
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	/**
	 * @return the appToken
	 */
	public String getAppToken() throws PartnerAuthenticationException{
		if(this.appToken == null){
			try {

				HeaderInterceptor intrc = new HeaderInterceptor("Finicity-App-Key", appKey);
				
				Credentials credentials = new Credentials();
				credentials.setPartnerId(partnerId);
				credentials.setPartnerSecret(partnerSecret);
				RestClient restClient = applicationContext.getBean("restClient", RestClient.class);
				
				restClient.getInterceptors().add(intrc);
				restClient.getMessageConverters().add(new Jaxb2RootElementHttpMessageConverter());
				ResponseEntity<Access> resp = restClient.postForEntity(finicityAuthUrl, credentials, Access.class);
				appToken = resp.getBody().getToken();
			} catch (Exception e) {
				throw new PartnerAuthenticationException(e);
			}
		}
		return appToken;
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
	public Timestamp getLastPullTime() {
		return lastPullTime;
	}
	/**
	 * @param lastPullTime the lastPullTime to set
	 */
	public void setLastPullTime(Timestamp lastPullTime) {
		this.lastPullTime = lastPullTime;
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
		
	}
	
//	private WebClient getConfiguredClient() throws PartnerAuthenticationException {
//        return WebClient.fromClient(finicityAuthWebClient)
//        		.header(, appKey)
//        		.accept("application/xml")
//				.type("application/xml");
//    }
	
}
