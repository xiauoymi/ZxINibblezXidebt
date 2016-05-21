package com.nibbledebt.integration.sao.plaid;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nibbledebt.common.error.ServiceException;
import com.nibbledebt.integration.model.plaid.Institution;
import com.nibbledebt.integration.model.plaid.LinkResponse;
import com.nibbledebt.integration.model.plaid.MfaResponse;
import com.nibbledebt.integration.model.plaid.TransactionsResponse;
import com.nibbledebt.integration.sao.IAccountSao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
public class PlaidSaoTest {
	@Autowired
	private IAccountSao plaidSao;
	
	@Test
	public void testUpgrade() throws ServiceException, JsonProcessingException{
		Institution institution = plaidSao.getInstitution("5301aa096b3f822b440001cb");
		LinkResponse resp = plaidSao.linkAccount("plaid_test", "plaid_good", "", institution);
//		UpgradeResponse uresp = plaidSao.upgrade(resp.getAccessToken());
//		assertNotNull(uresp);
//		System.out.println(ToStringBuilder.reflectionToString(uresp));
	}
	
	@Test
	public void testAuth() throws ServiceException, JsonProcessingException{
		Institution institution = plaidSao.getInstitution("5301aa096b3f822b440001cb");
		LinkResponse resp = plaidSao.linkAccount("plaid_test", "plaid_good", "", institution);
		assertNotNull(resp);
		System.out.println(ToStringBuilder.reflectionToString(resp));
	}
	
	@Test
	public void testAuthMfaSelections() throws ServiceException, JsonProcessingException{
		Institution institution = plaidSao.getInstitution("5301aa306b3f82a00500018b");
		MfaResponse resp = plaidSao.linkAccountMfa("plaid_selections", "plaid_good", "", institution);
		plaidSao.submitMfaResponse(resp.getAccessToken(), new String("[ \"tomato\", \"ketchup\"]"), institution);
		assertNotNull(resp);
		System.out.println(ToStringBuilder.reflectionToString(resp));
	}
	
	@Test
	public void testAuthMfaQuestions() throws ServiceException, JsonProcessingException{
		Institution institution = plaidSao.getInstitution("531ea6602ad939b68700047c");
		MfaResponse resp = plaidSao.linkAccountMfa("plaid_test", "plaid_good", "1234", institution);
		plaidSao.submitMfaResponse(resp.getAccessToken(), new String("tomato"), institution);
		assertNotNull(resp);
		System.out.println(ToStringBuilder.reflectionToString(resp));
	}
	
	@Test
	public void testAuthMfaCode() throws ServiceException, JsonProcessingException{
		Institution institution = plaidSao.getInstitution("5301a99504977c52b60000d0");
		MfaResponse resp = plaidSao.linkAccountMfa("plaid_test", "plaid_good", "1234", institution);
		plaidSao.submitMfaResponse(resp.getAccessToken(), new String("1234"), institution);
		assertNotNull(resp);
		System.out.println(ToStringBuilder.reflectionToString(resp));
	}
	
	@Test
	public void testGetAllInstitutions() throws ServiceException {
		List<Institution> institutions = plaidSao.getSupportedInstitutions();
		assertNotNull(institutions);
		assertEquals(12, institutions.size());
	}
	
	@Test
	public void testGetChaseInstitution() throws ServiceException {
		Institution institution = plaidSao.getInstitution("5301a99504977c52b60000d0");
		assertNotNull(institution);
		assertEquals("Chase", institution.getName());
	}
	
	@Test
	public void testGetBofaInstitution() throws ServiceException {
		Institution institution = plaidSao.getInstitution("5301a93ac140de84910000e0");
		assertNotNull(institution);
		assertEquals("Bank of America", institution.getName());
	}
	
	@Test
	public void testGetUsaaInstitution() throws ServiceException {
		Institution institution = plaidSao.getInstitution("531ea6602ad939b68700047c");
		assertNotNull(institution);
		assertEquals("USAA", institution.getName());
	}
	
	@Test
	public void testGetWellsInstitution() throws ServiceException {
		Institution institution = plaidSao.getInstitution("5301aa096b3f822b440001cb");
		assertNotNull(institution);
		assertEquals("Wells Fargo", institution.getName());
	} 
	
	@Test
	public void testTransactions() throws ServiceException {
		Institution institution = plaidSao.getInstitution("5301aa096b3f822b440001cb");
		LinkResponse lresp = plaidSao.linkAccount("plaid_test", "plaid_good", "", institution);
		TransactionsResponse resp = plaidSao.retrieveTransactions(lresp.getAccessToken(), lresp.getAccounts().get(0).getId(), "September 2014");
		assertNotNull(resp);
		assertEquals(16, resp.getTransactions().size());
	} 
}