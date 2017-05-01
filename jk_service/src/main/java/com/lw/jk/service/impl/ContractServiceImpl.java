package com.lw.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.lw.jk.dao.BaseDao;
import com.lw.jk.pojo.Contract;
import com.lw.jk.service.ContractService;
import com.lw.jk.utils.Page;
import com.lw.jk.utils.UtilFuns;


public class ContractServiceImpl implements ContractService {

	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Contract> find(String hql, Class<Contract> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	public Contract get(Class<Contract> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public Page<Contract> findPage(String hql, Page<Contract> page, Class<Contract> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	public void saveOrUpdate(Contract entity) {
		if (UtilFuns.isEmpty(entity.getId())) { // 新增
			// 新增
			entity.setTotalAmount(0d);
			entity.setState(0);// 0草稿 1已上报 2已报运
		}
		baseDao.saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<Contract> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<Contract> entityClass, Serializable id) {

		baseDao.deleteById(entityClass, id);// 删除一个对象
	}

	public void delete(Class<Contract> entityClass, Serializable[] ids) {

		for (Serializable id : ids) {
			this.deleteById(Contract.class, id);
		}
	}
	/**
	 *  修改状态
	 */
	@Override
	public void changeState(String[] ids, Integer state) {
		for (String id : ids) {
			// 根据ID查询每条Contract
			Contract contract = baseDao.get(Contract.class, id);
			contract.setState(state);
			// 可以根据hibernate的一级缓存可以不用写
			baseDao.saveOrUpdate(contract);
		}
	}

}
