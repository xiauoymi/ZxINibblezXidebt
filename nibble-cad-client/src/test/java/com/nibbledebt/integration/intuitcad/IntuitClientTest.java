/**
 * 
 */
package com.nibbledebt.integration.intuitcad;

import javax.annotation.Resource;

import org.dozer.Mapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nibbledebt.intuit.cad.exception.AggCatException;
import com.nibbledebt.intuit.cad.service.AggCatServiceFactory;

/**
 * @author alam_home
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class IntuitClientTest {
		
	@Resource
	private Environment env;
	
	@Value("${intuit.consumer.key}")
	private String consumerKey;
	
	@Value("${intuit.consumer.secret}")
	private String consumerSecret;
	
	@Value("${intuit.saml.id}")
	private String samlId;
	
	@Autowired
	private Mapper cadMapper;
	
//	@Test
//	public void testGetTrxs() throws FinicityAccessException{
//		Assert.assertNotNull(finicityClient.getCustomerAccountTransactions("5049649", "6444339", new Date(1435952168000l), new Date(1445952168000l), 1, 1000, "desc").getTransaction());
//	}
//	
//	@Test
//	public void getToken() throws FinicityAccessException, PartnerAuthenticationException {
//		Assert.assertNotNull(finicitySecurityContext.getAppToken());
//	}
	
	@Test
	public void getInstitutions() throws AggCatException  {
		AggCatServiceFactory.getService(consumerKey, consumerSecret, samlId, "sysuser").getInstitutions().getInstitutions();
	}
	
//	@Test
//	public void getInstitution()  {
//		AggCatServiceFactory.getService(consumerKey, consumerSecret, samlId, "sysuser").getInstitutionDetails(Long.valueOf(institutionIdentifier))
//	}
//	
//	@Test
//	public void addTestCustomer() throws FinicityAccessException {
//		Assert.assertNotNull(finicityClient.addTestCustomer("testcustomer1", "testfname", "testlname"));
//	}
//	
//	@Test
//    public void discoverCustomerAccount() throws FinicityAccessException {
//        LoginField fieldName = new LoginField();
//        fieldName.setId("101732001");
//        fieldName.setName("Banking Userid");
//        fieldName.setValue("tfa_text");
//
//        LoginField passField = new LoginField();
//        passField.setId("101732002");
//        passField.setName("Banking Password");
//        passField.setValue("go");
//        LoginField[] fields = new LoginField[]{fieldName, passField};
//        Accounts accounts = accountClient.discoverCustomerAccounts(
//                "4969749", "101732", fields);
//        Assert.assertNotNull(accounts);
//    }
//
//    @Test
//    public void discoverCustomerAccountStr() throws FinicityAccessException {
//        LoginField fieldName = new LoginField();
//        fieldName.setId("101732001");
//        fieldName.setName("Banking Userid");
//        fieldName.setValue("demo");
//
//        LoginField passField = new LoginField();
//        passField.setId("101732002");
//        passField.setName("Banking Password");
//        passField.setValue("go");
//        LoginField[] fields = new LoginField[]{fieldName, passField};
//        ResponseEntity<String> accounts = accountClient.discoverCustomerAccountsString(
//                "4969749", "101732", fields);
//        Assert.assertNotNull(accounts);
//    }
//
//    @Test
//    public void discoverWithMfa() throws FinicityAccessException, IOException {
//        LoginField fieldName = new LoginField();
//        fieldName.setId("101732001");
//        fieldName.setName("Banking Userid");
//        fieldName.setValue("tfa_text");
//
//        LoginField passField = new LoginField();
//        passField.setId("101732002");
//        passField.setName("Banking Password");
//        passField.setValue("go");
//        LoginField[] fields = new LoginField[]{fieldName, passField};
//        ResponseEntity<String> accounts = accountClient.discoverCustomerAccountsString(
//                "4969749", "101732", fields);
//        XmlMapper mapper = new XmlMapper();
//        TextMfaChallenges mfa = mapper.readValue(accounts.getBody(), TextMfaChallenges.class);
//        ResponseEntity<String> responseEntity =
//                accountClient.discoverCustomerAccountsMfaString("4969749", "101732", fields,
//                        accounts.getHeaders().get(AccountClient.MFA_SESSION_HEADER).get(0),
//                        mfa.getQuestion()[0].getText(), "answer");
//        Assert.assertNotNull(responseEntity);
//    }
//
//    @Test
//    public void deleteCustomers() throws FinicityAccessException, IOException {
//        Customers customers= finicityClient.getCustomers();
//        customers.getCustomers();
//        for (Customer customer : customers.getCustomers()) {
//            finicityClient.deleteCustomer(customer.getId());
//        }
//    }
//
//    @Test
//    public void addAccounts() throws FinicityAccessException, IOException {
//        LoginField fieldName = new LoginField();
//        fieldName.setId("101732001");
//        fieldName.setName("Banking Userid");
//        fieldName.setValue("tfa_text");
//
//        LoginField passField = new LoginField();
//        passField.setId("101732002");
//        passField.setName("Banking Password");
//        passField.setValue("go");
//        LoginField[] fields = new LoginField[]{fieldName, passField};
//        ResponseEntity<String> accounts = accountClient.addAllCustomerAccountsString(
//                "4969749", "101732", fields);
//        Assert.assertNotNull(accounts);
//        XmlMapper mapper = new XmlMapper();
//        TextMfaChallenges mfa = mapper.readValue(accounts.getBody(), TextMfaChallenges.class);
//        ResponseEntity<String> responseEntity =
//                accountClient.addAllCustomerAccountsMfaString("4969749", "101732",
//                        accounts.getHeaders().get(AccountClient.MFA_SESSION_HEADER).get(0),
//                        mfa.getQuestion()[0].getText(), "answer");
//        Assert.assertNotNull(responseEntity);
//    }
//
//    @Test
//    public void testOt() throws IOException {
//        XmlMapper mapper = new XmlMapper();
//        ImageChoiceMfaChallenges challenges = new ImageChoiceMfaChallenges();
//        ImageChoiceQuestion question = new ImageChoiceQuestion();
//        question.setText("Question ? :");
//        ImageChoice imageChoice = new ImageChoice();
//        imageChoice.setValue("foot");
//        imageChoice.setBody("body");
//        question.setImageChoice(new ImageChoice[]{imageChoice});
//        ImageChoiceQuestion question2 = new ImageChoiceQuestion();
//        question2.setText("Question2 ? :");
//        question2.setImageChoice(new ImageChoice[]{imageChoice, imageChoice, imageChoice});
//        challenges.setQuestion(new ImageChoiceQuestion[]{question, question2});
//        String str = mapper.writeValueAsString(challenges);
//        Institution inst = mapper.readValue(str, Institution.class);
//        Assert.assertNotNull(str);
//
//    }

}
