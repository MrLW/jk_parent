package com.lw.jk.service.impl;

import java.util.List;

import com.lw.jk.dao.impl.SqlDao;
import com.lw.jk.service.StatChartService;

public class StatChartServiceImpl implements StatChartService {
	
	private SqlDao sqlDao;

	public void setSqlDao(SqlDao sqlDao) {
		this.sqlDao = sqlDao;
	}

	@Override
	public List executeSQL(String sql) {
		return sqlDao.executeSQL(sql);
	}

}
