package com.lw.jk.action.cargo;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.lw.jk.action.BaseAction;
import com.lw.jk.pojo.PackingList;
import com.lw.jk.pojo.ShippingOrder;
import com.lw.jk.pojo.User;
import com.lw.jk.service.PackingListService;
import com.lw.jk.service.ShippingOrderService;
import com.lw.jk.utils.Page;
import com.lw.jk.utils.SysConstant;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @Description:	ShippingOrder
 * @Author:			
 * @Company:		
 * @CreateDate:		2017-4-30 8:17:21
 */

public class ShippingOrderAction extends BaseAction implements ModelDriven<ShippingOrder> {
	//注入service
	private ShippingOrderService shippingOrderService;
	public void setShippingOrderService(ShippingOrderService shippingOrderService) {
		this.shippingOrderService = shippingOrderService;
	}
	private PackingListService packingListService;
	public void setPackingListService(PackingListService packingListService) {
		this.packingListService = packingListService;
	}
	//model驱动
	private ShippingOrder model = new ShippingOrder();
	public ShippingOrder getModel() {
		return this.model;
	}
	
	//作为属性驱动，接收并封装页面参数
	private Page page = new Page();			//封装页面的参数，主要当前页参数
	public void setPage(Page page) {
		this.page = page;
	}


	//列表展示
	public String list(){
		
		// 清除缓存(其实就是ID,避免再次更新的时候)
		String hql = "from ShippingOrder o";			//查询所有内容
		//给页面提供分页数据
		page.setUrl("shippingOrderAction_list");		//配置分页按钮的转向链接
		page = shippingOrderService.findPage(hql, page, ShippingOrder.class, null);
		super.push(page);
		
		return "plist";						//page list
	}
	
	//转向新增页面
	public String tocreate(){
		// 查找所有已装箱的记录
		List<PackingList> packingList = packingListService.find("from PackingList where state = 1", PackingList.class, null);
		// 存入栈顶
		super.put("packingList", packingList);
		System.out.println("list:" + packingList);
		return "pcreate";
	}
	
	//新增保存
	public String insert(){
		// 设置字段
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.CURRENT_USER_INFO);
		model.setCreateBy(user.getUserName());
		model.setCreateDept(user.getCreateDept());
		model.setCreateTime(new Date());
		shippingOrderService.saveOrUpdate(model,packingListId);
		return "alist";			//返回列表，重定向action_list
	}

	//转向修改页面
	public String toupdate(){
		//准备数据
//		List<ShippingOrder> shippingOrderList = shippingOrderService.shippingOrderList();
//		super.put("shippingOrderList", shippingOrderList);		//页面就可以访问shippingOrderList
				
		//准备修改的数据
		ShippingOrder obj = shippingOrderService.get(ShippingOrder.class, model.getId());
		super.push(obj);
		
		return "pupdate";
	}
	
	//修改保存
	public String update(){
		ShippingOrder shippingOrder = shippingOrderService.get(ShippingOrder.class, model.getId());
		
		//设置修改的属性，根据业务去掉自动生成多余的属性
		shippingOrder.setOrderType(model.getOrderType());
		shippingOrder.setShipper(model.getShipper());
		shippingOrder.setConsignee(model.getConsignee());
		shippingOrder.setNotifyParty(model.getNotifyParty());
		shippingOrder.setLcNo(model.getLcNo());
		shippingOrder.setPortOfLoading(model.getPortOfLoading());
		shippingOrder.setPortOfTrans(model.getPortOfTrans());
		shippingOrder.setPortOfDischarge(model.getPortOfDischarge());
		shippingOrder.setLoadingDate(model.getLoadingDate());
		shippingOrder.setLimitDate(model.getLimitDate());
		shippingOrder.setIsBatch(model.getIsBatch());
		shippingOrder.setIsTrans(model.getIsTrans());
		shippingOrder.setCopyNum(model.getCopyNum());
		shippingOrder.setRemark(model.getRemark());
		shippingOrder.setSpecialCondition(model.getSpecialCondition());
		shippingOrder.setFreight(model.getFreight());
		
		shippingOrderService.saveOrUpdate(shippingOrder,packingListId);
		
		return "alist";
	}
	
	//删除一条
	public String deleteById(){
		shippingOrderService.deleteById(ShippingOrder.class, model.getId());
		
		return "alist";
	}
	
	
	//删除多条
	public String delete(){
		shippingOrderService.delete(ShippingOrder.class, model.getId().split(", "));
		
		return "alist";
	}
	
	//查看
	public String toview(){
		ShippingOrder obj = shippingOrderService.get(ShippingOrder.class, model.getId());
		super.push(obj);
		
		return "pview";			//转向查看页面
	}
	// 提交
	public String commit(){
		String[] ids = model.getId().split(", ");
		shippingOrderService.changeState(ids, 1);
		return "alist" ;
	}
	
	// 取消
	public String cancel(){
		String[] ids = model.getId().split(", ");
		shippingOrderService.changeState(ids,0);
		return "alist" ;
	}
	
	private String packingListId ;
	public String getPackingListId() {
		return packingListId;
	}


	public void setPackingListId(String packingListId) {
		this.packingListId = packingListId;
	}
	
	
}
