/**
 * 
 */
package com.nibbledebt.integration.finicity;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.nibbledebt.integration.finicity.error.FinicityAccessException;
import com.nibbledebt.integration.finicity.error.PartnerAuthenticationException;
import com.nibbledebt.integration.finicity.model.Institution;
import com.nibbledebt.integration.finicity.model.LoginField;
import com.nibbledebt.integration.finicity.model.accounts.Accounts;
import com.nibbledebt.integration.finicity.model.accounts.ImageChoice;
import com.nibbledebt.integration.finicity.model.accounts.ImageChoiceMfaChallenges;
import com.nibbledebt.integration.finicity.model.accounts.ImageChoiceQuestion;
import com.nibbledebt.integration.finicity.model.accounts.TextMfaChallenges;

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
	

	@Autowired
	AccountClient accountClient;
	
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
    public void discoverCustomerAccount() throws FinicityAccessException {
        LoginField fieldName = new LoginField();
        fieldName.setId("101732001");
        fieldName.setName("Banking Userid");
        fieldName.setValue("tfa_text");

        LoginField passField = new LoginField();
        passField.setId("101732002");
        passField.setName("Banking Password");
        passField.setValue("go");
        LoginField[] fields = new LoginField[]{fieldName, passField};
        Accounts accounts = accountClient.discoverCustomerAccounts(
                "4969749", "101732", fields);
        Assert.assertNotNull(accounts);
    }

    @Test
    public void discoverCustomerAccountStr() throws FinicityAccessException {
        LoginField fieldName = new LoginField();
        fieldName.setId("101732001");
        fieldName.setName("Banking Userid");
        fieldName.setValue("demo");

        LoginField passField = new LoginField();
        passField.setId("101732002");
        passField.setName("Banking Password");
        passField.setValue("go");
        LoginField[] fields = new LoginField[]{fieldName, passField};
        ResponseEntity<String> accounts = accountClient.discoverCustomerAccountsString(
                "4969749", "101732", fields);
        Assert.assertNotNull(accounts);
    }

    @Test
    public void discoverWithMfa() throws FinicityAccessException, IOException {
        LoginField fieldName = new LoginField();
        fieldName.setId("101732001");
        fieldName.setName("Banking Userid");
        fieldName.setValue("tfa_text");

        LoginField passField = new LoginField();
        passField.setId("101732002");
        passField.setName("Banking Password");
        passField.setValue("go");
        LoginField[] fields = new LoginField[]{fieldName, passField};
        ResponseEntity<String> accounts = accountClient.discoverCustomerAccountsString(
                "4969749", "101732", fields);
        XmlMapper mapper = new XmlMapper();
        TextMfaChallenges mfa = mapper.readValue(accounts.getBody(), TextMfaChallenges.class);
        ResponseEntity<String> responseEntity =
                accountClient.discoverCustomerAccountsMfaString("4969749", "101732", fields,
                        accounts.getHeaders().get(AccountClient.MFA_SESSION_HEADER).get(0),
                        mfa.getQuestion()[0].getText(), "answer");
        Assert.assertNotNull(responseEntity);
    }

    @Test
    public void addAccounts() throws FinicityAccessException, IOException {
        LoginField fieldName = new LoginField();
        fieldName.setId("101732001");
        fieldName.setName("Banking Userid");
        fieldName.setValue("tfa_text");

        LoginField passField = new LoginField();
        passField.setId("101732002");
        passField.setName("Banking Password");
        passField.setValue("go");
        LoginField[] fields = new LoginField[]{fieldName, passField};
        ResponseEntity<String> accounts = accountClient.addAllCustomerAccountsString(
                "4969749", "101732", fields);
        Assert.assertNotNull(accounts);
        XmlMapper mapper = new XmlMapper();
        TextMfaChallenges mfa = mapper.readValue(accounts.getBody(), TextMfaChallenges.class);
        ResponseEntity<String> responseEntity =
                accountClient.addAllCustomerAccountsMfaString("4969749", "101732",
                        accounts.getHeaders().get(AccountClient.MFA_SESSION_HEADER).get(0),
                        mfa.getQuestion()[0].getText(), "answer");
        Assert.assertNotNull(responseEntity);
    }

    @Test
    public void testOt() throws IOException {
        XmlMapper mapper = new XmlMapper();
        ImageChoiceMfaChallenges challenges = new ImageChoiceMfaChallenges();
        ImageChoiceQuestion question = new ImageChoiceQuestion();
        question.setText("Question ? :");
        ImageChoice imageChoice = new ImageChoice();
        imageChoice.setValue("foot");
        imageChoice.setBody("body");
        question.setImageChoice(new ImageChoice[]{imageChoice});
        ImageChoiceQuestion question2 = new ImageChoiceQuestion();
        question2.setText("Question2 ? :");
        question2.setImageChoice(new ImageChoice[]{imageChoice, imageChoice, imageChoice});
        challenges.setQuestion(new ImageChoiceQuestion[]{question, question2});
        String str = mapper.writeValueAsString(challenges);
        Institution inst = mapper.readValue(str, Institution.class);
        Assert.assertNotNull(str);

    }

}
