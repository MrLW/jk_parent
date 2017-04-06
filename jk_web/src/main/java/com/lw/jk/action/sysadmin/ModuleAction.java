package com.lw.jk.action.sysadmin;

import java.util.List;

import com.lw.jk.action.BaseAction;
import com.lw.jk.pojo.Dept;
import com.lw.jk.pojo.Module;
import com.lw.jk.service.DeptService;
import com.lw.jk.service.ModuleService;
import com.lw.jk.utils.Page;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 部门管理
 * 
 * @author lw
 *
 */
public class ModuleAction extends BaseAction implements ModelDriven<Module> {

	private Module model = new Module();
	// 分页
	private Page page = new Page<>();

	private ModuleService moduleService;

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	@Override
	public Module getModel() {
		return model;
	}
	
	public Page getPage() {
		return page;
	}

	/**
	 * 分页查询
	 */
	public String list() throws Exception {
		// 将page对象压入栈顶
		super.push(page);
		// 注意：这里传递的是引用数据类型，因此这里可以用page接收，也可以不用
		// page =
		// HQL查询
		moduleService.findPage("from Module", page, Module.class, null);
		// 设置分页URL
		page.setUrl("moduleAction_list");
		return "list";
	}

	/**
	 * 查看
	 */
	public String toview() throws Exception {
		// 根據ID查询对象
		Module dept = moduleService.get(Module.class, model.getId());
		// 存入值栈
		super.push(dept);
		return "toview";
	}

	/**
	 * 新增
	 */
	public String tocreate() throws Exception {
		// 3、跳转页面
		return "tocreate";
	}

	/**
	 * 新增部门
	 */
	public String insert() throws Exception {
		System.out.println("执行了module的新增操作");
		// 从前台提交的数据有：父部门的name，新家部门的name，因此我们需要在service中处理
		moduleService.saveOrUpdate(model);
		return "alist";
	}

	/**
	 * 删除 因为可能删除的不只是1条数据，因此需要注意： 如果id是string类型的，则id属性会使用逗号分隔符
	 * 如果id是integer类型的，则只会取最后一条id值
	 */
	public String delete() throws Exception {
		// 1、需要将要删除的部门分割开
		String[] ids = model.getId().split(", ");
		// 2、调用service的方法一次性删除
		moduleService.delete(Module.class, ids);
		// 3、返回页面
		return "alist"; // 删除成功后返回到列表页面
	}

	/**
	 * 进入修改页面
	 */
	public String toupdate() throws Exception {
		Module module = moduleService.get(Module.class, model.getId());
		// 放入值栈顶
		super.push(module);

		return "toupdate";
	}

	/**
	 * 修改
	 */
	public String update() throws Exception {
		// 找到要修改的对象
		Module obj = moduleService.get(Module.class, model.getId());
		// 设置要修改的属性
		obj.setName(model.getName());
		obj.setLayerNum(model.getLayerNum());
		obj.setCpermission(model.getCpermission());
		obj.setCurl(model.getCurl());
		obj.setCtype(model.getCtype());
		obj.setState(model.getState());
		obj.setBelong(model.getBelong());
		obj.setCwhich(model.getCwhich());
		obj.setRemark(model.getRemark());
		obj.setOrderNo(model.getOrderNo());
		moduleService.saveOrUpdate(obj);
		return "alist" ;
	}

}
