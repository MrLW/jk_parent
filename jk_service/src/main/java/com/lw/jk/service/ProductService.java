package com.lw.jk.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.lw.jk.pojo.Product;
import com.lw.jk.utils.Page;

/**
 * @Description:	Product
 * @Author:			
 * @Company:		
 * @CreateDate:		2017-5-1 20:33:13
 */

public interface ProductService {

	public List<Product> find(String hql, Class<Product> entityClass, Object[] params);
	public Product get(Class<Product> entityClass, Serializable id);
	public Page<Product> findPage(String hql, Page<Product> page, Class<Product> entityClass, Object[] params);
	
	public void saveOrUpdate(Product entity);
	public void saveOrUpdateAll(Collection<Product> entitys);
	
	public void deleteById(Class<Product> entityClass, Serializable id);
	public void delete(Class<Product> entityClass, Serializable[] ids);
}
