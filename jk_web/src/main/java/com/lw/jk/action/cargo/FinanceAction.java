package com.lw.jk.action.cargo;

import java.util.Date;
import java.util.List;

import com.lw.jk.action.BaseAction;
import com.lw.jk.pojo.Finance;
import com.lw.jk.pojo.Invoice;
import com.lw.jk.pojo.User;
import com.lw.jk.service.FinanceService;
import com.lw.jk.service.InvoiceService;
import com.lw.jk.utils.Page;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @Description: 财务操作
 * @Author: lw
 * @Company:
 * @CreateDate: 2017-4-30 19:46:33
 */

public class FinanceAction extends BaseAction implements ModelDriven<Finance> {
	// 注入service
	private FinanceService financeService;
	private InvoiceService invoiceService;

	public void setFinanceService(FinanceService financeService) {
		this.financeService = financeService;
	}

	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	// model驱动
	private Finance model = new Finance();

	public Finance getModel() {
		return this.model;
	}

	// 作为属性驱动，接收并封装页面参数
	private Page page = new Page(); // 封装页面的参数，主要当前页参数

	public void setPage(Page page) {
		this.page = page;
	}

	// 列表展示
	public String list() {
		String hql = "from Finance o"; // 查询所有内容
		// 给页面提供分页数据
		page.setUrl("financeAction_list"); // 配置分页按钮的转向链接
		page = financeService.findPage(hql, page, Finance.class, null);
		super.push(page);

		return "plist"; // page list
	}

	// 转向新增页面
	public String tocreate() {
		// 查询所有状态为1的发票
		List<Invoice> invoiceList = invoiceService.find("from Invoice where state = 1 ", Invoice.class, null);
		super.put("invoiceList", invoiceList);
		return "pcreate";
	}

	// 新增保存
	public String insert() {
		User user = super.getCurrentUser();
		model.setCreateBy(user.getCreateBy());
		model.setCreateDept(user.getCreateDept());
		model.setCreateTime(new Date());	
		financeService.saveOrUpdate(model,invoiceId);
		
		return "alist"; // 返回列表，重定向action_list
	}

	// 转向修改页面
	public String toupdate() {
		// 准备数据
		// List<Finance> financeList = financeService.financeList();
		// super.put("financeList", financeList); //页面就可以访问financeList

		// 准备修改的数据
		Finance obj = financeService.get(Finance.class, model.getId());
		super.push(obj);

		return "pupdate";
	}

	// 修改保存
	public String update() {
		Finance finance = financeService.get(Finance.class, model.getId());

		// 设置修改的属性，根据业务去掉自动生成多余的属性
		finance.setInputDate(model.getInputDate());
		finance.setInputBy(model.getInputBy());

		financeService.saveOrUpdate(finance,invoiceId);

		return "alist";
	}

	// 删除一条
	public String deleteById() {
		financeService.deleteById(Finance.class, model.getId());

		return "alist";
	}

	// 删除多条
	public String delete() {
		financeService.delete(Finance.class, model.getId().split(", "));

		return "alist";
	}

	// 查看
	public String toview() {
		Finance obj = financeService.get(Finance.class, model.getId());
		super.push(obj);

		return "pview"; // 转向查看页面
	}

	// 提交
	public String commit() {
		String[] ids = model.getId().split(", ");
		financeService.changeState(ids, 1);
		return "alist";
	}

	// 取消
	public String cancel() {
		String[] ids = model.getId().split(", ");
		financeService.changeState(ids, 0);
		return "alist";
	}
	
	private String invoiceId ;

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	

}
