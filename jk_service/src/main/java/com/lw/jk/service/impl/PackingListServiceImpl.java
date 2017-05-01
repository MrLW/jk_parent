package com.lw.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.lw.jk.dao.BaseDao;
import com.lw.jk.pojo.Export;
import com.lw.jk.pojo.PackingList;
import com.lw.jk.service.PackingListService;
import com.lw.jk.utils.Page;


/**
 * @Description: PackingList
 * @Author:
 * @Company:
 * @CreateDate: 2017-4-29 14:32:20
 */

public class PackingListServiceImpl implements PackingListService {
	// spring注入dao
	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<PackingList> find(String hql, Class<PackingList> entityClass, Object[] params) {
		return baseDao.find(hql, PackingList.class, params);
	}

	public PackingList get(Class<PackingList> entityClass, Serializable id) {
		return baseDao.get(PackingList.class, id);
	}

	public Page<PackingList> findPage(String hql, Page<PackingList> page, Class<PackingList> entityClass,
			Object[] params) {
		return baseDao.findPage(hql, page, PackingList.class, params);
	}

	public void saveOrUpdate(PackingList entity) {
		if (entity.getId() == null) { // 代表新增
			entity.setState(1); // 状态：0停用1启用 默认启用

			// 分割exportIds
			String[] exportIds = entity.getExportIds().split(", ");
			// 根据exportid查找export报运单对象
			String exportNos = "";
			for (String id : exportIds) {
				Export export = baseDao.get(Export.class, id);
				// 设置export的状态
				// export.setState(2); // 设置当前报运单为装箱状态,这里还是不要设置了
				// 拼接exportNos
				exportNos += export.getCustomerContract() + ",";
				// 设置当前No值
				baseDao.saveOrUpdate(export);
			}
			exportNos = exportNos.substring(0, exportNos.lastIndexOf(","));
			entity.setExportNos(exportNos);
		}
		// 设置报运集合
		baseDao.saveOrUpdate(entity);

	}

	public void saveOrUpdateAll(Collection<PackingList> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<PackingList> entityClass, Serializable id) {
		baseDao.deleteById(PackingList.class, id);
	}

	public void delete(Class<PackingList> entityClass, Serializable[] ids) {
		baseDao.delete(PackingList.class, ids);
	}

}
