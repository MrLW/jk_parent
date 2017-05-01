package com.lw.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.lw.jk.dao.BaseDao;
import com.lw.jk.pojo.ExportProduct;
import com.lw.jk.service.ExportProductService;
import com.lw.jk.utils.Page;
import com.lw.jk.utils.UtilFuns;


public class ExportProductServiceImpl implements ExportProductService {

	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<ExportProduct> find(String hql, Class<ExportProduct> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	public ExportProduct get(Class<ExportProduct> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public Page<ExportProduct> findPage(String hql, Page<ExportProduct> page, Class<ExportProduct> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	public void saveOrUpdate(ExportProduct entity) {
		if (UtilFuns.isEmpty(entity.getId())) { // 新增
			
		}
		baseDao.saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<ExportProduct> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<ExportProduct> entityClass, Serializable id) {

		baseDao.deleteById(entityClass, id);// 删除一个对象
	}

	public void delete(Class<ExportProduct> entityClass, Serializable[] ids) {

		for (Serializable id : ids) {
			this.deleteById(ExportProduct.class, id);
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
