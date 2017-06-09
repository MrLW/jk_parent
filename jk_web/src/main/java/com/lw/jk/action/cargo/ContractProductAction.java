package com.lw.jk.action.cargo;

import java.util.List;

import com.lw.jk.action.BaseAction;
import com.lw.jk.pojo.ContractProduct;
import com.lw.jk.pojo.Factory;
import com.lw.jk.service.ContractProductService;
import com.lw.jk.service.FactoryService;
import com.lw.jk.utils.Page;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 购销合同的货物管理
 * 
 * @author lw
 *
 */
public class ContractProductAction extends BaseAction implements ModelDriven<ContractProduct> {

	private ContractProduct model = new ContractProduct();
	// 分页
	private Page page = new Page<>();

	private ContractProductService contractProductService;

	private FactoryService factoryService;

	public void setContractProductService(ContractProductService contractProductService) {
		this.contractProductService = contractProductService;
	}

	public void setFactoryService(FactoryService factoryService) {
		this.factoryService = factoryService;
	}

	@Override
	public ContractProduct getModel() {
		return model;
	}

	public Page getPage() {
		return page;
	}

	/**
	 * 进入到新增页面
	 */
	public String tocreate() throws Exception {
		// 1、查询生产获取的厂家
		List<Factory> factoryList = getFactoryList();
		// 存入值栈
		super.put("factoryList", factoryList);

		// 查询当前购销合同下的货物列表
		contractProductService.findPage("from ContractProduct where contract.id=?", page, ContractProduct.class,
				new String[] { model.getContract().getId() });

		// 设置分页的URL
		page.setUrl("contractProductAction_tocreate");

		super.push(page);

		return "tocreate";
	}

	/**
	 * 执行新增货物
	 */
	public String insert() throws Exception {
		model.setId(null);
		contractProductService.saveOrUpdate(model);

		return tocreate();
	}

	/**
	 * 进入到修改页面 这里的修改页面和新增一样
	 */
	public String toupdate() throws Exception {
		// 1、通过Id查询CP对象
		ContractProduct cp = contractProductService.get(ContractProduct.class, model.getId());
		// 2、将cp放入栈顶
		super.push(cp);
		// 加载生产厂家列表
		List<Factory> factoryList = getFactoryList();
		// 将其放入值栈
		super.put("factoryList", factoryList);

		return "toupdate";
	}

	/**
	 * 更新操作
	 */
	public String update() throws Exception {
		// 1、 根据ID查询数据库中的对象
		ContractProduct cp = contractProductService.get(ContractProduct.class, model.getId());
		// 2、设置要修改的属性
		cp.setFactory(model.getFactory());
		cp.setFactoryName(model.getFactoryName());
		cp.setProductNo(model.getProductNo());
		cp.setProductImage(model.getProductImage());
		cp.setCnumber(model.getCnumber());
		cp.setAmount(model.getAmount());
		cp.setPackingUnit(model.getPackingUnit());
		cp.setLoadingRate(model.getLoadingRate());
		cp.setBoxNum(model.getBoxNum());
		cp.setPrice(model.getPrice());
		cp.setOrderNo(model.getOrderNo());
		cp.setProductDesc(model.getProductDesc());
		cp.setProductRequest(model.getProductRequest());

		// 3、执行更新操作
		contractProductService.saveOrUpdate(cp);

		return tocreate();
	}

	/**
	 * 执行删除操作
	 */
	public String delete() throws Exception {
		contractProductService.delete(ContractProduct.class, model);
		return tocreate();
	}

	/**
	 * 加载生产厂家列表
	 */
	private List<Factory> getFactoryList() {
		String hql = "from Factory where ctype = '货物' and state =1 ";
		List<Factory> factoryList = factoryService.find(hql, Factory.class, null);
		return factoryList;
	}

}
