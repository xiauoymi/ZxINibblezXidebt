/**
 * 
 */
package com.nibbledebt.integration.sao.intuit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.nibbledebt.common.error.ServiceException;
import com.nibbledebt.domain.model.Institution;
import com.nibbledebt.domain.model.LoginField;
import com.nibbledebt.domain.model.LoginForm;
import com.nibbledebt.domain.model.Transaction;
import com.nibbledebt.domain.model.account.AddAccountsResponse;
import com.nibbledebt.integration.sao.IIntegrationSao;
import com.nibbledebt.intuit.cad.data.InstitutionDetail;
import com.nibbledebt.intuit.cad.data.InstitutionDetail.Keys.Key;
import com.nibbledebt.intuit.cad.service.AggCatServiceFactory;

/**
 * @author alam_home
 *
 */
@Component("intuitCadSao")
public class IntuitCASDSao implements IIntegrationSao {
	@Resource
	private List<String> intuitSuppInstitutionTypes;
	
	@Resource
	private List<String> intuitTestInstitutionTypes;

	@Resource
	private List<String> intuitSuppLoanTypes;
		
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
	
	/* (non-Javadoc)
	 * @see com.nibbledebt.integration.sao.IIntegrationSao#getInstitutions()
	 */
	@Override
	public List<Institution> getInstitutions() throws ServiceException {
		List<Institution> institutions = new ArrayList<>();
		try {
			for(com.nibbledebt.intuit.cad.data.Institution finst : AggCatServiceFactory.getService(consumerKey, consumerSecret, samlId, "sysuser").getInstitutions().getInstitutions()){
				institutions.add(cadMapper.map(finst, Institution.class));
			}
			return institutions;
		} catch (Exception e) {
			throw new ServiceException("Error while retrieving the supported institutions from Intuit.", e);
		}
	}

	/* (non-Javadoc)
	 * @see com.nibbledebt.integration.sao.IIntegrationSao#getInstitution(java.lang.String)
	 */
	@Override
	public Institution getInstitution(String institutionIdentifier) throws ServiceException {
		try {
			InstitutionDetail instDetail = AggCatServiceFactory.getService(consumerKey, consumerSecret, samlId, "sysuser").getInstitutionDetails(Long.valueOf(institutionIdentifier));
			Institution detail = cadMapper.map(instDetail, Institution.class);
			LoginForm loginForm = new LoginForm();
			List<LoginField> fields = new ArrayList<>();
			for(Key key : instDetail.getKeys().getKeies()){
				fields.add(cadMapper.map(key, LoginField.class));
			}
			loginForm.setLoginField(fields);
			detail.setLoginForm(loginForm);
			return detail;
		} catch (Exception e) {
			throw new ServiceException("Error while retrieving the supported institution from Finicity with id:"+institutionIdentifier, e);
		}
	}

	/* (non-Javadoc)
	 * @see com.nibbledebt.integration.sao.IIntegrationSao#getInstitutionLoginForm(java.lang.String)
	 */
	@Override
	public LoginForm getInstitutionLoginForm(String institutionIdentifier) throws ServiceException {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nibbledebt.integration.sao.IIntegrationSao#addAccounts(java.lang.String, java.lang.String, com.nibbledebt.domain.model.LoginField[])
	 */
	@Override
	public AddAccountsResponse addAccounts(String customerId, String institutionId, LoginField[] fields) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nibbledebt.integration.sao.IIntegrationSao#addAccountsMfaAnswer(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public AddAccountsResponse addAccountsMfaAnswer(String customerId, String institutionId, String question, String answer) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nibbledebt.integration.sao.IIntegrationSao#addCustomer(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String addCustomer(String userName, String firstName, String lastName) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nibbledebt.integration.sao.IIntegrationSao#deleteCustomer(java.lang.String)
	 */
	@Override
	public void deleteCustomer(String customerId) throws ServiceException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.nibbledebt.integration.sao.IIntegrationSao#retrieveTransactions(java.lang.String, java.lang.String, java.util.Date, java.util.Date, java.lang.String)
	 */
	@Override
	public List<Transaction> retrieveTransactions(String customerId, String accountId, Date fromDate, Date toDate, String sort) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * @return the suppInstitutionTypes
	 */
	@Override
	public List<String> getSuppInstitutionTypes() {
		return intuitSuppInstitutionTypes;
	}

	/**
	 * @return the testInstitutionTypes
	 */
	@Override
	public List<String> getTestInstitutionTypes() {
		return intuitTestInstitutionTypes;
	}

	/**
	 * @return the suppLoanTypes
	 */
	@Override
	public List<String> getSuppLoanTypes() {
		return intuitSuppLoanTypes;
	}
}