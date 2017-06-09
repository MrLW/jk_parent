package com.lw.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.lw.jk.dao.BaseDao;
import com.lw.jk.pojo.LoginLog;
import com.lw.jk.service.LoginLogService;
import com.lw.jk.utils.Page;

/**
 * @Description:	LoginLog
 * @Author:			
 * @Company:		
 * @CreateDate:		2017-5-2 18:30:16
 */

public class LoginLogServiceImpl implements LoginLogService {
	//spring注入dao
	private BaseDao baseDao;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	public void saveOrUpdate(LoginLog entity) {
		baseDao.saveOrUpdate(entity);
	}
}
