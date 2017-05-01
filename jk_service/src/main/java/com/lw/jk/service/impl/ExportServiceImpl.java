package com.lw.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.lw.jk.dao.BaseDao;
import com.lw.jk.pojo.Contract;
import com.lw.jk.pojo.ContractProduct;
import com.lw.jk.pojo.Export;
import com.lw.jk.pojo.ExportProduct;
import com.lw.jk.pojo.ExtCproduct;
import com.lw.jk.pojo.ExtEproduct;
import com.lw.jk.service.ExportService;
import com.lw.jk.utils.Page;
import com.lw.jk.utils.UtilFuns;


public class ExportServiceImpl implements ExportService {

	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Export> find(String hql, Class<Export> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	public Export get(Class<Export> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public Page<Export> findPage(String hql, Page<Export> page, Class<Export> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	public void saveOrUpdate(Export entity) {
		if (UtilFuns.isEmpty(entity.getId())) { // 新增
			// 新增报运单,所以此时的状态为0
			entity.setState(0);
			String[] ids = entity.getContractIds().split(", ");
			StringBuilder sb = new StringBuilder();
			// 遍历购销合同
			for (String id : ids) {
				// 获取要报运的购销合同
				Contract contract = baseDao.get(Contract.class, id);
				// 0草稿 1已上报2待报运
				contract.setState(2);
				// 更新数据库中的状态
				baseDao.saveOrUpdate(contract);
				sb.append(contract.getContractNo()).append(" ");
			}
			// 设置当前合同和确认书号
			entity.setCustomerContract(sb.toString());
			entity.setContractIds(UtilFuns.joinStr(ids, ","));

			// ******通过购销集合,跳跃查询购销合同下面的货物列表 ******
			String hql = "from ContractProduct where contract.id in (" + UtilFuns.joinInStr(ids) + ")";
			// 查询购销合同下的所有货物列表
			List<ContractProduct> list = baseDao.find(hql, ContractProduct.class, null);
			// 数据搬家
			Set<ExportProduct> epSet = new HashSet<ExportProduct>();
			for (ContractProduct cp : list) {
				ExportProduct ep = new ExportProduct();// 报运单下的商品
				ep.setBoxNum(cp.getBoxNum());
				ep.setCnumber(cp.getCnumber());
				ep.setFactory(cp.getFactory());
				ep.setOrderNo(cp.getOrderNo());
				ep.setPackingUnit(cp.getPackingUnit());
				ep.setPrice(cp.getPrice());
				ep.setProductNo(cp.getProductNo());
				// 因为在一对多的关系映射中,由一的一方维系
				ep.setExport(entity);// 设置商品与报运单 多对一
				epSet.add(ep);

				// 加载购销合同下当前货物的附件列表
				Set<ExtCproduct> extCSet = cp.getExtCproducts();
				Set<ExtEproduct> extESet = new HashSet<ExtEproduct>();// 报运单下的附件列表
				for (ExtCproduct extC : extCSet) {
					ExtEproduct extE = new ExtEproduct();

					// 拷贝对象的属性
					BeanUtils.copyProperties(extC, extE);
					// 注意：不能把ID也拷贝了,由UUID自动生成
					extE.setId(null);
					// ******同上面购销合同上面的货物和报运商品类似,由一的一方维护关系 ******
					extE.setExportProduct(ep);// 附件与商品 多对一

					extESet.add(extE);// 向列表中添加元素
				}
				// 向报运单添加附件列表
				ep.setExtEproducts(extESet);
			}
			// 设置保运单的货物集合
			entity.setExportProducts(epSet);
		}
		baseDao.saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<Export> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<Export> entityClass, Serializable id) {

		baseDao.deleteById(entityClass, id);// 删除一个对象
	}

	public void delete(Class<Export> entityClass, Serializable[] ids) {

		for (Serializable id : ids) {
			this.deleteById(Export.class, id);
		}
	}

	/**
	 * 修改状态
	 */
	@Override
	public void changeState(String[] ids, Integer state) {
		for (String id : ids) {
			// 根据ID查询每条Export
			Export contract = baseDao.get(Export.class, id);
			contract.setState(state);
			// 可以根据hibernate的一级缓存可以不用写
			baseDao.saveOrUpdate(contract);
		}
	}

	@Override
	public void updateE(Export resultExport) {
		// 先查询再修改
		Export export = baseDao.get(Export.class, resultExport.getId());
		// 设置报运单的属性
		export.setState(resultExport.getState());
		export.setRemark(resultExport.getRemark());
		// 加载报运单下每个商品
		Set<ExportProduct> exportProducts = resultExport.getExportProducts();
		for (ExportProduct ep : exportProducts) {
			ExportProduct epp = baseDao.get(ExportProduct.class, ep.getId());
			// 修改每个商品的税金
			epp.setTax(ep.getTax());
			baseDao.saveOrUpdate(epp);
		}
		// 保存修改结果
		baseDao.saveOrUpdate(export);
	}

}
