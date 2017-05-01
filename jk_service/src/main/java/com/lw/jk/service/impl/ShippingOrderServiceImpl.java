package com.lw.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.lw.jk.dao.BaseDao;
import com.lw.jk.pojo.ShippingOrder;
import com.lw.jk.service.ShippingOrderService;
import com.lw.jk.utils.Page;

/**
 * @Description: ShippingOrder
 * @Author:
 * @Company:
 * @CreateDate: 2017-4-30 8:17:21
 */

public class ShippingOrderServiceImpl implements ShippingOrderService {
	// spring注入dao
	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<ShippingOrder> find(String hql, Class<ShippingOrder> entityClass, Object[] params) {
		return baseDao.find(hql, ShippingOrder.class, params);
	}

	public ShippingOrder get(Class<ShippingOrder> entityClass, Serializable id) {
		return baseDao.get(ShippingOrder.class, id);
	}

	public Page<ShippingOrder> findPage(String hql, Page<ShippingOrder> page, Class<ShippingOrder> entityClass,
			Object[] params) {
		return baseDao.findPage(hql, page, ShippingOrder.class, params);
	}

	public void saveOrUpdate(ShippingOrder entity, String id) {
		System.out.println(entity);
		if (entity.getId() == null) { // 代表新增
			// 设置ID
			entity.setId(id);
			entity.setState(1); // 状态：0停用1启用 默认启用
		}
		baseDao.saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<ShippingOrder> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<ShippingOrder> entityClass, Serializable id) {
		baseDao.deleteById(ShippingOrder.class, id);
	}

	public void delete(Class<ShippingOrder> entityClass, Serializable[] ids) {
		baseDao.delete(ShippingOrder.class, ids);
	}

	@Override
	public void changeState(String[] ids, int state) {
		for (String id : ids) {
			ShippingOrder so = baseDao.get(ShippingOrder.class, id);
			so.setState(state);
			baseDao.saveOrUpdate(so);
		}
	}

}
