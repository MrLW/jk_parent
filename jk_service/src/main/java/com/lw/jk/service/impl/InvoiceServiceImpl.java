package com.lw.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.lw.jk.dao.BaseDao;
import com.lw.jk.pojo.Invoice;
import com.lw.jk.service.InvoiceService;
import com.lw.jk.utils.Page;

/**
 * @Description:	Invoice
 * @Author:			
 * @Company:		
 * @CreateDate:		2017-4-30 16:15:16
 */

public class InvoiceServiceImpl implements InvoiceService {
	//spring注入dao
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Invoice> find(String hql, Class<Invoice> entityClass, Object[] params) {
		return baseDao.find(hql, Invoice.class, params);
	}

	public Invoice get(Class<Invoice> entityClass, Serializable id) {
		return baseDao.get(Invoice.class, id);
	}

	public Page<Invoice> findPage(String hql, Page<Invoice> page, Class<Invoice> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, Invoice.class, params);
	}

	public void saveOrUpdate(Invoice entity,String id) {
		if(entity.getId()==null){								//代表新增
			// 给发票设置ID
			entity.setId(id);
			entity.setState(1);									//状态：0停用1启用 默认启用
		}
		baseDao.saveOrUpdate(entity);
	}



	public void saveOrUpdateAll(Collection<Invoice> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<Invoice> entityClass, Serializable id) {
		baseDao.deleteById(Invoice.class, id);
	}

	public void delete(Class<Invoice> entityClass, Serializable[] ids) {
		baseDao.delete(Invoice.class, ids);
	}

	@Override
	public void changeState(String[] ids, int state) {
		for (String id : ids) {
			Invoice invo = baseDao.get(Invoice.class, id);
			invo.setState(state);
			baseDao.saveOrUpdate(invo);
		}
		
	}

}
