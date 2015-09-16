/**
 * 
 */
package com.nibbledebt.integration.finicity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nibbledebt.integration.finicity.error.FinicityAccessException;

/**
 * @author alam_home
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class FinicityClientTest {

	@Autowired
	FinicityClient finicityClient;
	
	@Test
	public void getInstitutions() throws FinicityAccessException {
		finicityClient.getInstitutions();
	}

}
