package com.lw.jk.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.lw.jk.pojo.Invoice;
import com.lw.jk.utils.Page;


/**
 * @Description:	Invoice
 * @Author:			
 * @Company:		
 * @CreateDate:		2017-4-30 16:15:16
 */

public interface InvoiceService {

	public List<Invoice> find(String hql, Class<Invoice> entityClass, Object[] params);
	public Invoice get(Class<Invoice> entityClass, Serializable id);
	public Page<Invoice> findPage(String hql, Page<Invoice> page, Class<Invoice> entityClass, Object[] params);
	
	public void saveOrUpdate(Invoice entity,String id);
	public void saveOrUpdateAll(Collection<Invoice> entitys);
	
	public void deleteById(Class<Invoice> entityClass, Serializable id);
	public void delete(Class<Invoice> entityClass, Serializable[] ids);
	public void changeState(String[] ids, int state);
}
