package com.lw.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.apache.poi.ss.formula.functions.T;

import com.lw.jk.dao.BaseDao;
import com.lw.jk.pojo.User;
import com.lw.jk.service.UserService;
import com.lw.jk.utils.Page;
import com.lw.jk.utils.UtilFuns;

public class UserServiceImpl implements UserService {

	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<User> find(String hql, Class<User> entityClass, Object[] params) {

		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public User get(Class<User> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<User> findPage(String hql, Page<User> page, Class<User> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	@Override
	public void saveOrUpdate(User entity) {
		// 判断是否是新加的部门
		if (UtilFuns.isEmpty(entity.getId())) {
			// 设置新家的部门启用
			entity.setState(1);
			// 生成UUID并且设置
			String uuid = UUID.randomUUID().toString();
			entity.setId(uuid);
			entity.getUserInfo().setId(uuid);
		}
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<User> entitys) {

	}

	/** 删除用户
	 */
	@Override
	public void deleteById(Class<User> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<User> entityClass, Serializable[] ids) {
		// 遍历调用上面删除一个id的方法
		for (Serializable id : ids) {
			this.deleteById(User.class, id);
		}
	}

}
