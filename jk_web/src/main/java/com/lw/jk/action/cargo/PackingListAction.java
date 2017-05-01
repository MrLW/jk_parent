package com.lw.jk.action.cargo;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.lw.jk.action.BaseAction;
import com.lw.jk.pojo.Export;
import com.lw.jk.pojo.PackingList;
import com.lw.jk.pojo.User;
import com.lw.jk.service.ExportService;
import com.lw.jk.service.PackingListService;
import com.lw.jk.utils.Page;
import com.lw.jk.utils.SysConstant;
import com.opensymphony.xwork2.ModelDriven;

/**
 *  装箱管理
 * @CreateDate:		2017-4-29 14:32:21
 */

public class PackingListAction extends BaseAction implements ModelDriven<PackingList> {
	//注入service
	private PackingListService packingListService;
	
	public void setPackingListService(PackingListService packingListService) {
		this.packingListService = packingListService;
	}
	
	private ExportService exportService;
	
	public void setExportService(ExportService exportService) {
		this.exportService = exportService;
	}
	
	//model驱动
	private PackingList model = new PackingList();
	public PackingList getModel() {
		return this.model;
	}
	
	//作为属性驱动，接收并封装页面参数
	private Page page = new Page();			//封装页面的参数，主要当前页参数
	public void setPage(Page page) {
		this.page = page;
	}


	//列表展示
	public String list(){
		String hql = "from PackingList o";			//查询所有内容
		//给页面提供分页数据
		page.setUrl("packingListAction_list");		//配置分页按钮的转向链接
		page = packingListService.findPage(hql, page, PackingList.class, null);
		super.push(page);
		
		
		
		return "plist";						//page list
	}
	
	//转向新增页面
	public String tocreate(){
		// 查询报运单记录
		List<Export> exportList = exportService.find("from Export ", Export.class, null);
		// 存入值栈中
		super.put("exportList", exportList);;
		
		return "pcreate";
	}
	
	//新增装箱
	public String insert(){
		User user =(User) ServletActionContext.getRequest().getSession().getAttribute(SysConstant.CURRENT_USER_INFO);
		model.setCreateBy(user.getUserName());
		model.setCreateDept(user.getDept().getDeptName());
		model.setCreateTime(new Date());
		packingListService.saveOrUpdate(model);
		return "alist";			//返回列表，重定向action_list
	}

	//转向修改页面
	public String toupdate(){
		//准备数据
//		List<PackingList> packingListList = packingListService.packingListList();
//		super.put("packingListList", packingListList);		//页面就可以访问packingListList
				
		//准备修改的数据
		PackingList obj = packingListService.get(PackingList.class, model.getId());
		super.push(obj);
		
		return "pupdate";
	}
	
	//修改保存
	public String update(){
		PackingList packingList = packingListService.get(PackingList.class, model.getId());
		
		//设置修改的属性，根据业务去掉自动生成多余的属性
//		packingList.setPackingListId(model.getPackingListId());
		packingList.setSeller(model.getSeller());
		packingList.setBuyer(model.getBuyer());
		packingList.setInvoiceNo(model.getInvoiceNo());
		packingList.setInvoiceDate(model.getInvoiceDate());
		packingList.setMarks(model.getMarks());
		packingList.setDescriptions(model.getDescriptions());
		packingList.setExportIds(model.getExportIds());
		packingList.setExportNos(model.getExportNos());
		packingList.setState(model.getState());
		packingList.setCreateBy(model.getCreateBy());
		packingList.setCreateDept(model.getCreateDept());
		packingList.setCreateTime(model.getCreateTime());
		
		packingListService.saveOrUpdate(packingList);
		
		return "alist";
	}
	
	//删除一条
	public String deleteById(){
		packingListService.deleteById(PackingList.class, model.getId());
		
		return "alist";
	}
	
	
	//删除多条
	public String delete(){
		packingListService.delete(PackingList.class, model.getId().split(", "));
		return "alist";
	}
	
	//查看
	public String toview(){
		PackingList obj = packingListService.get(PackingList.class, model.getId());
		super.push(obj);
		
		return "pview";			//转向查看页面
	}

	private String exportIds ;

	public String getExportIds() {
		return exportIds;
	}


	public void setExportIds(String exportIds) {
		this.exportIds = exportIds;
	}

	
	
}	
	
	
	
