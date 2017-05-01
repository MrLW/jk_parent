package com.lw.jk.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.lw.jk.pojo.PackingList;
import com.lw.jk.utils.Page;


/**
 * @Description:	PackingList
 * @Author:			
 * @Company:		
 * @CreateDate:		2017-4-29 14:32:20
 */

public interface PackingListService {

	public List<PackingList> find(String hql, Class<PackingList> entityClass, Object[] params);
	public PackingList get(Class<PackingList> entityClass, Serializable id);
	public Page<PackingList> findPage(String hql, Page<PackingList> page, Class<PackingList> entityClass, Object[] params);
	
	public void saveOrUpdate(PackingList entity);
	public void saveOrUpdateAll(Collection<PackingList> entitys);
	
	public void deleteById(Class<PackingList> entityClass, Serializable id);
	public void delete(Class<PackingList> entityClass, Serializable[] ids);
}
