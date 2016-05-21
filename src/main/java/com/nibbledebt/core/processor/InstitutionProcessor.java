/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.processor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.nibbledebt.common.error.ProcessingException;
import com.nibbledebt.common.error.ServiceException;
import com.nibbledebt.integration.model.plaid.Institution;
import com.nibbledebt.integration.sao.IAccountSao;

/**
 * @author ralam1
 *
 */
@Component
public class InstitutionProcessor {
	private static final String LOGO_LOCATION = "com/nibbledebt/institutions/";
	private static final String[] SUPPORTED_TYPES = {"amex", "capone360", "citi", "fidelity", "pnc", "schwab", "svb", "us", "wells", "bofa", "usaa", "chase"};
	@Autowired
	private IAccountSao plaidSao;
	
	@Cacheable(value="instCache")
	@Transactional(readOnly=true)
	public List<Institution> getSupportedInstitutions() throws ProcessingException, ServiceException{
		List<Institution> plaidInsts = plaidSao.getSupportedInstitutions();
		List<Institution> suppInsts = new ArrayList<>();
		List<String> suppTypes = Arrays.asList(SUPPORTED_TYPES);
		for(Institution inst : plaidInsts){
			if(suppTypes.contains(inst.getType()) && inst.getProducts().contains("connect")
					&& inst.getProducts().contains("auth"))
				suppInsts.add(inst);
		}
		return suppInsts;
	}
	
	@Cacheable(value="logoCache")
	@Transactional(readOnly=true)
	public byte[] getLogo(String institutionType) throws ProcessingException{
		try {	
			return IOUtils.toByteArray(this.getClass().getClassLoader().getResourceAsStream(LOGO_LOCATION+institutionType));
		} catch (IOException e) {
			throw new ProcessingException("Error while converting logo inputstream to byte[].", e);
		}
	}
	
}
