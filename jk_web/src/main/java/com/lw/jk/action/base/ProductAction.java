package com.lw.jk.action.base;

import java.util.Date;
import java.util.List;

import com.lw.jk.action.BaseAction;
import com.lw.jk.pojo.Factory;
import com.lw.jk.pojo.Product;
import com.lw.jk.pojo.User;
import com.lw.jk.service.FactoryService;
import com.lw.jk.service.ProductService;
import com.lw.jk.utils.Page;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @Description: Product
 * @Author:
 * @Company:
 * @CreateDate: 2017-5-1 20:33:13
 */

public class ProductAction extends BaseAction implements ModelDriven<Product> {
	// 注入service
	private ProductService productService;
	private FactoryService factoryService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setFactoryService(FactoryService factoryService) {
		this.factoryService = factoryService;
	}

	// model驱动
	private Product model = new Product();

	public Product getModel() {
		return this.model;
	}

	// 作为属性驱动，接收并封装页面参数
	private Page page = new Page(); // 封装页面的参数，主要当前页参数

	public void setPage(Page page) {
		this.page = page;
	}

	// 列表展示
	public String list() {
		String hql = "from Product o"; // 查询所有内容
		// 给页面提供分页数据
		page.setUrl("productAction_list"); // 配置分页按钮的转向链接
		page = productService.findPage(hql, page, Product.class, null);
		super.push(page);

		return "plist"; // page list
	}

	// 转向新增页面
	public String tocreate() {
		// 查询所有工厂信息
		List<Factory> factoryList = factoryService.find("from Factory", Factory.class, null);
		// 存入contextMap
		super.put("factoryList", factoryList);
		return "pcreate";
	}

	// 新增保存
	public String insert() {
		User user = super.getCurrentUser();
		model.setFactoryId(model.getFactoryId().split(",")[1]);
		model.setCreateBy(user.getUserName());
		model.setCreateDept(user.getCreateDept());
		model.setCreateTime(new Date());
		productService.saveOrUpdate(model);

		return "alist"; // 返回列表，重定向action_list
	}

	// 转向修改页面
	public String toupdate() {
		// 准备数据
		// 查询所有工厂信息
		List<Factory> factoryList = factoryService.find("from Factory", Factory.class, null);
		// 存入contextMap
		super.put("factoryList", factoryList);

		// 准备修改的数据
		Product obj = productService.get(Product.class, model.getId());
		super.push(obj);

		return "pupdate";
	}

	// 修改保存
	public String update() {
		// 设置一下factoryId
		model.setFactoryId(model.getFactoryId().split(",")[1]);
		Product product = productService.get(Product.class, model.getId());
		// 设置修改的属性，根据业务去掉自动生成多余的属性
		product.setProductNo(model.getProductNo());
		product.setProductImage(model.getProductImage());
		product.setDescription(model.getDescription());
		product.setFactoryId(model.getFactoryId());
		product.setFactoryName(model.getFactoryName());
		product.setPrice(model.getPrice());
		product.setSizeLenght(model.getSizeLenght());
		product.setSizeWidth(model.getSizeWidth());
		product.setSizeHeight(model.getSizeHeight());
		product.setColor(model.getColor());
		product.setPacking(model.getPacking());
		product.setPackingUnit(model.getPackingUnit());
		product.setType20(model.getType20());
		product.setType40(model.getType40());
		product.setType40hc(model.getType40hc());
		product.setQty(model.getQty());
		product.setCbm(model.getCbm());
		product.setMpsizeLenght(model.getMpsizeLenght());
		product.setMpsizeWidth(model.getMpsizeWidth());
		product.setMpsizeHeight(model.getMpsizeHeight());
		product.setRemark(model.getRemark());
		productService.saveOrUpdate(product);

		return "alist";
	}

	// 删除一条
	public String deleteById() {
		productService.deleteById(Product.class, model.getId());

		return "alist";
	}

	// 删除多条
	public String delete() {
		productService.delete(Product.class, model.getId().split(", "));

		return "alist";
	}

	// 查看
	public String toview() {
		
		// 查询所有工厂
		List<Factory> factoryList = factoryService.find("from Factory", Factory.class, null);
		super.put("factoryList", factoryList);
		Product obj = productService.get(Product.class, model.getId());
		super.push(obj);

		return "pview"; // 转向查看页面
	}

	/**
	 * 根据工厂全称查询工厂简称
	 */
	public String getFactoryName() {
		System.out.println("getFactoryName");
		return "jianchen";
	}
}
