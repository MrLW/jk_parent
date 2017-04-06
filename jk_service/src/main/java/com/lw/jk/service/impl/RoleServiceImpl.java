package com.lw.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.lw.jk.dao.BaseDao;
import com.lw.jk.pojo.Role;
import com.lw.jk.service.RoleService;
import com.lw.jk.utils.Page;

public class RoleServiceImpl implements RoleService {

	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<Role> find(String hql, Class<Role> entityClass, Object[] params) {

		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public Role get(Class<Role> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<Role> findPage(String hql, Page<Role> page, Class<Role> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	@Override
	public void saveOrUpdate(Role entity) {
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<Role> entitys) {

	}

	/** 删除用户
	 */
	@Override
	public void deleteById(Class<Role> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<Role> entityClass, Serializable[] ids) {
		// 遍历调用上面删除一个id的方法
		for (Serializable id : ids) {
			this.deleteById(Role.class, id);
		}
	}

}
