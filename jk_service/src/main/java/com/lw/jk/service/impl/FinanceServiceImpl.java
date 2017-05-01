package com.lw.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.lw.jk.dao.BaseDao;
import com.lw.jk.pojo.Finance;
import com.lw.jk.pojo.Invoice;
import com.lw.jk.service.FinanceService;
import com.lw.jk.utils.Page;


/**
 * @Description:	Finance
 * @Author:			
 * @Company:		
 * @CreateDate:		2017-4-30 19:46:33
 */

public class FinanceServiceImpl implements FinanceService {
	//spring注入dao
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Finance> find(String hql, Class<Finance> entityClass, Object[] params) {
		return baseDao.find(hql, Finance.class, params);
	}

	public Finance get(Class<Finance> entityClass, Serializable id) {
		return baseDao.get(Finance.class, id);
	}

	public Page<Finance> findPage(String hql, Page<Finance> page, Class<Finance> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, Finance.class, params);
	}

	public void saveOrUpdate(Finance entity,String id) {
		if(entity.getId()==null){								//代表新增
			entity.setState(1);									//状态：0停用1启用 默认启用
			// 设置id
			entity.setId(id);
		}
		baseDao.saveOrUpdate(entity);
	}



	public void saveOrUpdateAll(Collection<Finance> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<Finance> entityClass, Serializable id) {
		baseDao.deleteById(Finance.class, id);
	}

	public void delete(Class<Finance> entityClass, Serializable[] ids) {
		baseDao.delete(Finance.class, ids);
	}

	@Override
	public void changeState(String[] ids, int state) {
		for (String id : ids) {
			Finance fina = baseDao.get(Finance.class, id);
			fina.setState(state);
			baseDao.saveOrUpdate(fina);
		}		
	}

}
