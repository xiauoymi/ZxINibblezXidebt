/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.nibbledebt.core.data.error.RepositoryException;
import com.nibbledebt.core.data.model.ChartImage;

/**
 * @author ust3000
 *
 */
@Repository
public class ChartImageDao extends AbstractHibernateDao<ChartImage> implements IChartImageDao {

	public ChartImageDao() {
		super(ChartImage.class);
	}

	@Transactional
	@Override
	public ChartImage findOne(long id) throws RepositoryException {
		return super.findOne(id);
	}
	
	
	
}
