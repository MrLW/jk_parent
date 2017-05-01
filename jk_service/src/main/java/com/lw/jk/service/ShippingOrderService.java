package com.lw.jk.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.lw.jk.pojo.ShippingOrder;
import com.lw.jk.utils.Page;


/**
 * @Description:	ShippingOrder
 * @Author:			
 * @Company:		
 * @CreateDate:		2017-4-30 8:17:21
 */

public interface ShippingOrderService {

	public List<ShippingOrder> find(String hql, Class<ShippingOrder> entityClass, Object[] params);
	public ShippingOrder get(Class<ShippingOrder> entityClass, Serializable id);
	public Page<ShippingOrder> findPage(String hql, Page<ShippingOrder> page, Class<ShippingOrder> entityClass, Object[] params);
	
	public void saveOrUpdate(ShippingOrder entity,String packingListId);
	public void saveOrUpdateAll(Collection<ShippingOrder> entitys);
	
	public void deleteById(Class<ShippingOrder> entityClass, Serializable id);
	public void delete(Class<ShippingOrder> entityClass, Serializable[] ids);
	public void changeState(String[] ids , int state);
}
