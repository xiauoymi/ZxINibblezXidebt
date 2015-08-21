package com.nibbledebt.intuit.cad.interceptor;

import java.util.HashMap;
import java.util.Map;

import com.nibbledebt.intuit.cad.data.AccountList;
import com.nibbledebt.intuit.cad.data.Challenges;
import com.nibbledebt.intuit.cad.data.Files;
import com.nibbledebt.intuit.cad.data.InstitutionDetail;
import com.nibbledebt.intuit.cad.data.Institutions;
import com.nibbledebt.intuit.cad.data.InvestmentPositions;
import com.nibbledebt.intuit.cad.data.Status;
import com.nibbledebt.intuit.cad.data.TransactionList;

public final class ResourceTypeLocator {
	private static Map<String, Class<?>> resourceTypeMap = new HashMap();
	public static final String GET_INSTITUTIONS = "GetInstitutions";
	public static final String GET_INSTITUTION_DETAILS = "GetInstitutionDetails";
	public static final String DISCOVER_AND_ADD_ACCOUNTS = "DiscoverAndAddAccounts";
	public static final String DISCOVER_AND_ADD_ACCOUNTS_CHALLENGE = "DiscoverAndAddAccountsChallenge";
	public static final String GET_CUSTOMER_ACCOUNTS = "GetCustomerAccounts";
	public static final String GET_LOGIN_ACCOUNTS = "GetLoginAccounts";
	public static final String GET_ACCOUNT = "GetAccount";
	public static final String GET_ACCOUNT_TRANSACTIONS = "GetAccountTransactions";
	public static final String UPDATE_INSTITUTION_LOGIN = "UpdateInstitutionLogin";
	public static final String UPDATE_INSTITUTION_LOGIN_CHALLEGE = "UpdateInstitutionLoginChallenge";
	public static final String UPDATE_ACCOUNT_TYPE = "UpdateAccountType";
	public static final String DELETE_ACCOUNT = "DeleteAccount";
	public static final String DELETE_CUSTOMER = "DeleteCustomer";
	public static final String LIST_FILES = "ListFiles";
	public static final String DELETE_FILE = "DeleteFile";
	public static final String GET_FILE_DATA = "GetFileData";
	public static final String GET_INVESTMENT_POSITIONS = "GetInvestmentPositions";
	public static final String STATUS = "Status";

	public static ResourceTypeLocator getInstance() {
		return new ResourceTypeLocator();
	}

	public static Class<?> getResourceType(String key) {
		return (Class) resourceTypeMap.get(key);
	}

	static {
		resourceTypeMap.put("GetInstitutions", Institutions.class);
		resourceTypeMap.put("GetInstitutionDetails", InstitutionDetail.class);
		resourceTypeMap.put("DiscoverAndAddAccounts", AccountList.class);
		resourceTypeMap
				.put("DiscoverAndAddAccountsChallenge", Challenges.class);
		resourceTypeMap.put("GetCustomerAccounts", AccountList.class);
		resourceTypeMap.put("GetLoginAccounts", AccountList.class);
		resourceTypeMap.put("GetAccount", AccountList.class);
		resourceTypeMap.put("GetAccountTransactions", TransactionList.class);
		resourceTypeMap
				.put("UpdateInstitutionLoginChallenge", Challenges.class);
		resourceTypeMap.put("ListFiles", Files.class);
		resourceTypeMap
				.put("GetInvestmentPositions", InvestmentPositions.class);
		resourceTypeMap.put("Status", Status.class);
	}
}