/**
 * 
 */
package com.nibbledebt.integration.finicity;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nibbledebt.integration.finicity.error.FinicityAccessException;
import com.nibbledebt.integration.finicity.error.PartnerAuthenticationException;
import com.nibbledebt.integration.finicity.model.LoginField;

/**
 * @author alam_home
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class FinicityClientTest {

	@Autowired
	SecurityContext finicitySecurityContext;
	
	@Autowired
	FinicityClient finicityClient;
	
	@Test
	public void getToken() throws FinicityAccessException, PartnerAuthenticationException {
		Assert.assertNotNull(finicitySecurityContext.getAppToken());
	}
	
	@Test
	public void getInstitutions() throws FinicityAccessException {
		Assert.assertNotNull(finicityClient.getInstitutions());
	}
	
	@Test
	public void getInstitution() throws FinicityAccessException {
		Assert.assertNotNull(finicityClient.getInstitution("101732"));
	}
	
	@Test
	public void addTestCustomer() throws FinicityAccessException {
		Assert.assertNotNull(finicityClient.addTestCustomer("testcustomer1", "testfname", "testlname"));
	}
	
	@Test
	public void discoverAccounts() throws FinicityAccessException {
		List<LoginField> fields = new ArrayList<>();
		Assert.assertNotNull(finicityClient.discoverAccounts(finicityClient.addTestCustomer("testcustomer1", "testfname", "testlname").getId(), 
							"101732", fields));
	}

}
