package com.lw.jk.action.base;


import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.lw.jk.action.BaseAction;
import com.lw.jk.pojo.Factory;
import com.lw.jk.pojo.User;
import com.lw.jk.service.FactoryService;
import com.lw.jk.utils.Page;
import com.lw.jk.utils.SysConstant;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @Description:	基础信息:Factory
 * @Author:			lw
 */

public class FactoryAction extends BaseAction implements ModelDriven<Factory> {
	//注入service
	private FactoryService factoryService;
	public void setFactoryService(FactoryService factoryService) {
		this.factoryService = factoryService;
	}
	
	//model驱动
	private Factory model = new Factory();
	public Factory getModel() {
		return this.model;
	}
	
	//作为属性驱动，接收并封装页面参数
	private Page page = new Page();			//封装页面的参数，主要当前页参数
	public void setPage(Page page) {
		this.page = page;
	}


	//列表展示
	public String list(){
		String hql = "from Factory o";			//查询所有内容
		//给页面提供分页数据
		page.setUrl("factoryAction_list");		//配置分页按钮的转向链接
		page = factoryService.findPage(hql, page, Factory.class, null);
		super.push(page);
		
		return "plist";						//page list
	}
	
	//转向新增页面
	public String tocreate(){
		//准备数据
//		List<Factory> factoryList = factoryService.factoryList();
//		super.put("factoryList", factoryList);		//页面就可以访问factoryList
		
		return "pcreate";
	}
	
	//新增保存
	public String insert(){
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.CURRENT_USER_INFO);
		// 设置创建信息
		model.setCreateBy(user.getUserName());
		model.setCreateDept(user.getCreateDept());
		model.setCreateTime(new Date());
		factoryService.saveOrUpdate(model);
		
		return "alist";			//返回列表，重定向action_list
	}

	//转向修改页面
	public String toupdate(){
		//准备修改的数据
		Factory obj = factoryService.get(Factory.class, model.getId());
		super.push(obj);
		
		return "pupdate";
	}
	
	//修改保存
	public String update(){
		System.out.println("1111111111");
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.CURRENT_USER_INFO);
		System.out.println("22222222222");
		Factory factory = factoryService.get(Factory.class, model.getId());
		System.out.println("333333333333");
		//设置修改的属性，根据业务去掉自动生成多余的属性
		factory.setCtype(model.getCtype());
		factory.setFullName(model.getFullName());
		factory.setFactoryName(model.getFactoryName());
		factory.setContacts(model.getContacts());
		factory.setPhone(model.getPhone());
		factory.setMobile(model.getMobile());
		factory.setFax(model.getFax());
		factory.setAddress(model.getAddress());
		factory.setInspector(model.getInspector());
		factory.setRemark(model.getRemark());
		factory.setOrderNo(model.getOrderNo());
		factory.setState(model.getState());
		factory.setUpdateBy(user.getCreateBy());
		factory.setUpdateTime(new Date());
		
		factoryService.saveOrUpdate(factory);
		
		return "alist";
	}
	
	//删除一条
	public String deleteById(){
		factoryService.deleteById(Factory.class, model.getId());
		
		return "alist";
	}
	
	
	//删除多条
	public String delete(){
		factoryService.delete(Factory.class, model.getId().split(", "));
		
		return "alist";
	}
	
	//查看
	public String toview(){
		Factory obj = factoryService.get(Factory.class, model.getId());
		super.push(obj);
		
		return "pview";			//转向查看页面
	}
}
