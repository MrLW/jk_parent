package com.lw.jk.action.cargo;

import java.util.List;

import com.lw.jk.action.BaseAction;
import com.lw.jk.pojo.Invoice;
import com.lw.jk.pojo.ShippingOrder;
import com.lw.jk.service.InvoiceService;
import com.lw.jk.service.ShippingOrderService;
import com.lw.jk.utils.Page;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @Description: Invoice
 * @Author:
 * @Company:
 * @CreateDate: 2017-4-30 16:15:16
 */

public class InvoiceAction extends BaseAction implements ModelDriven<Invoice> {
	// 注入service
	private InvoiceService invoiceService;
	private ShippingOrderService shippingOrderService;
	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	public void setShippingOrderService(ShippingOrderService shippingOrderService) {
		this.shippingOrderService = shippingOrderService;
	}

	// model驱动
	private Invoice model = new Invoice();

	public Invoice getModel() {
		return this.model;
	}

	// 作为属性驱动，接收并封装页面参数
	private Page page = new Page(); // 封装页面的参数，主要当前页参数

	public void setPage(Page page) {
		this.page = page;
	}

	// 列表展示
	public String list() {
		String hql = "from Invoice o"; // 查询所有内容
		// 给页面提供分页数据
		page.setUrl("invoiceAction_list"); // 配置分页按钮的转向链接
		page = invoiceService.findPage(hql, page, Invoice.class, null);
		super.push(page);

		return "plist"; // page list
	}

	// 转向新增页面
	public String tocreate() {
		// 查找委托状态为1的委托单
		List<ShippingOrder> shippingOrderList = shippingOrderService.find("from ShippingOrder where state = 1 ", ShippingOrder.class, null);
		// 存入栈顶
		super.put("shippingOrderList", shippingOrderList);
		return "pcreate";
	}

	// 新增保存
	public String insert() {
		
		invoiceService.saveOrUpdate(model,shippingOrderListId);
		
		return "alist"; // 返回列表，重定向action_list
	}

	// 转向修改页面
	public String toupdate() {
		// 准备数据
		// List<Invoice> invoiceList = invoiceService.invoiceList();
		// super.put("invoiceList", invoiceList); // 页面就可以访问invoiceList

		// 准备修改的数据
		Invoice obj = invoiceService.get(Invoice.class, model.getId());
		super.push(obj);

		return "pupdate";
	}

	// 修改保存
	public String update() {
		Invoice invoice = invoiceService.get(Invoice.class, model.getId());

		// 设置修改的属性，根据业务去掉自动生成多余的属性
		// invoice.setInvoiceId(model.getInvoiceId());
		invoice.setScNo(model.getScNo());
		invoice.setBlNo(model.getBlNo());
		invoice.setTradeTerms(model.getTradeTerms());
		invoice.setState(model.getState());
		invoice.setCreateBy(model.getCreateBy());
		invoice.setCreateDept(model.getCreateDept());
		invoice.setCreateTime(model.getCreateTime());

		invoiceService.saveOrUpdate(invoice,shippingOrderListId);

		return "alist";
	}

	// 删除一条
	public String deleteById() {
		invoiceService.deleteById(Invoice.class, model.getId());

		return "alist";
	}

	// 删除多条
	public String delete() {
		invoiceService.delete(Invoice.class, model.getId().split(", "));

		return "alist";
	}

	// 查看
	public String toview() {
		Invoice obj = invoiceService.get(Invoice.class, model.getId());
		super.push(obj);

		return "pview"; // 转向查看页面
	}

	// 提交
	public String commit() {
		String[] ids = model.getId().split(", ");
		invoiceService.changeState(ids, 1);
		return "alist";
	}

	// 取消
	public String cancel() {
		String[] ids = model.getId().split(", ");
		invoiceService.changeState(ids, 0);
		return "alist";
	}
	
	
	// 用来设置Invoice的Id
	private String shippingOrderListId ;
	public void setShippingOrderListId(String shippingOrderListId) {
		this.shippingOrderListId = shippingOrderListId;
	}
	public String getShippingOrderListId() {
		return shippingOrderListId;
	}
}
