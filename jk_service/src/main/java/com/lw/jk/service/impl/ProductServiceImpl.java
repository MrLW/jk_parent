package com.lw.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.lw.jk.dao.BaseDao;
import com.lw.jk.pojo.Product;
import com.lw.jk.service.ProductService;
import com.lw.jk.utils.Page;

/**
 * @Description:	Product
 * @Author:			
 * @Company:		
 * @CreateDate:		2017-5-1 20:33:13
 */

public class ProductServiceImpl implements ProductService {
	//spring注入dao
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Product> find(String hql, Class<Product> entityClass, Object[] params) {
		return baseDao.find(hql, Product.class, params);
	}

	public Product get(Class<Product> entityClass, Serializable id) {
		return baseDao.get(Product.class, id);
	}

	public Page<Product> findPage(String hql, Page<Product> page, Class<Product> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, Product.class, params);
	}

	public void saveOrUpdate(Product entity) {
		if(entity.getId()==null){								//代表新增
			
		}
		baseDao.saveOrUpdate(entity);
	}



	public void saveOrUpdateAll(Collection<Product> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<Product> entityClass, Serializable id) {
		baseDao.deleteById(Product.class, id);
	}

	public void delete(Class<Product> entityClass, Serializable[] ids) {
		baseDao.delete(Product.class, ids);
	}

}
