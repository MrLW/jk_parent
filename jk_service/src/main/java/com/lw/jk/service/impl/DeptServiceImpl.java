package com.lw.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.lw.jk.dao.BaseDao;
import com.lw.jk.pojo.Dept;
import com.lw.jk.service.DeptService;
import com.lw.jk.utils.Page;
import com.lw.jk.utils.UtilFuns;

public class DeptServiceImpl implements DeptService {

	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<Dept> find(String hql, Class<Dept> entityClass, Object[] params) {

		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public Dept get(Class<Dept> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<Dept> findPage(String hql, Page<Dept> page, Class<Dept> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	@Override
	public void saveOrUpdate(Dept entity) {
		// 判断是否是新加的部门
		if (UtilFuns.isEmpty(entity.getId())) {
			// 设置新家的部门启用
			entity.setState(1);
		}
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<Dept> entitys) {

	}

	/**
	 * 根据Id删除部门. 注意：如果是父部门，则会将子部门也删除
	 */
	@Override
	public void deleteById(Class<Dept> entityClass, Serializable id) {
		// 1、判断当前删除的部门是否是父部门
		// 注意：这里对判断是父部门hql的写法
		String hql = "from Dept where parent.id=?";
		List<Dept> deptList = baseDao.find(hql, entityClass, new Object[] { id });
		if (deptList != null && deptList.size() > 0) { // 当前部门是父部门
			// 2、如果是父部门则递归调用本方法
			for (Dept dept : deptList) {
				deleteById(entityClass, id);
			}
		}
		// 3、如果当前删除的部门不是父部门,则删除该部门
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<Dept> entityClass, Serializable[] ids) {
		// 遍历调用上面删除一个id的方法
		for (Serializable id : ids) {
			this.deleteById(Dept.class, id);
		}
	}

}
