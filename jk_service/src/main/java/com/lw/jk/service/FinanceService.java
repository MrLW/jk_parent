package com.lw.jk.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.lw.jk.pojo.Finance;
import com.lw.jk.utils.Page;

/**
 * @Description:	Finance
 * @Author:			
 * @Company:		
 * @CreateDate:		2017-4-30 19:46:33
 */

public interface FinanceService {

	public List<Finance> find(String hql, Class<Finance> entityClass, Object[] params);
	public Finance get(Class<Finance> entityClass, Serializable id);
	public Page<Finance> findPage(String hql, Page<Finance> page, Class<Finance> entityClass, Object[] params);
	
	public void saveOrUpdate(Finance entity,String id);
	public void saveOrUpdateAll(Collection<Finance> entitys);
	
	public void deleteById(Class<Finance> entityClass, Serializable id);
	public void delete(Class<Finance> entityClass, Serializable[] ids);
	public void changeState(String[] ids, int state);
}
