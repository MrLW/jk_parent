package com.lw.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.lw.jk.dao.BaseDao;
import com.lw.jk.pojo.ExtEproduct;
import com.lw.jk.service.ExtEproductService;
import com.lw.jk.utils.Page;
import com.lw.jk.utils.UtilFuns;


public class ExtEproductServiceImpl implements ExtEproductService {

	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<ExtEproduct> find(String hql, Class<ExtEproduct> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	public ExtEproduct get(Class<ExtEproduct> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public Page<ExtEproduct> findPage(String hql, Page<ExtEproduct> page, Class<ExtEproduct> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	public void saveOrUpdate(ExtEproduct entity) {
		if (UtilFuns.isEmpty(entity.getId())) { // 新增
			
		}
		baseDao.saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<ExtEproduct> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<ExtEproduct> entityClass, Serializable id) {

		baseDao.deleteById(entityClass, id);// 删除一个对象
	}

	public void delete(Class<ExtEproduct> entityClass, Serializable[] ids) {

		for (Serializable id : ids) {
			this.deleteById(ExtEproduct.class, id);
		}
	}
	/**
	 *  修改状态
	 */
	@Override
	public void changeState(String[] ids, Integer state) {
		for (String id : ids) {
		}
	}

}
