package com.lw.jk.action.sysadmin;

import java.util.List;

import com.lw.jk.action.BaseAction;
import com.lw.jk.pojo.Dept;
import com.lw.jk.service.DeptService;
import com.lw.jk.utils.Page;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 部门管理
 * @author lw
 */
public class DeptAction extends BaseAction implements ModelDriven<Dept> {

	private Dept model = new Dept();
	// 分页
	private Page page = new Page<>();

	private DeptService deptService;

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}
	// 接受页面传递过来的pageNo，之前因为没有添加get方法，导致每次加载的都是第一个数据
	public Page getPage() {
		return page;
	}

	@Override
	public Dept getModel() {
		return model;
	}

	/**
	 * 分页查询
	 */
	public String list() throws Exception {
		// 注意：这里传递的是引用数据类型，因此这里可以用page接收，也可以不用
		// page =
		// HQL查询
		deptService.findPage("from Dept", page, Dept.class, null);
		// 将page对象压入栈顶
		super.push(page);
		// 设置分页URL
		page.setUrl("deptAction_list");
		return "list";
	}

	/**
	 * 查看
	 */
	public String toview() throws Exception {
		// 根據ID查询对象
		Dept dept = deptService.get(Dept.class, model.getId());
		// 存入值栈
		super.push(dept);

		return "toview";
	}

	/**
	 * 新增
	 */
	public String tocreate() throws Exception {
		saveDeptList();
		// 3、跳转页面
		return "tocreate";
	}

	/**
	 * 新增部门
	 */
	public String insert() throws Exception {
		model.setId(null);
		// 从前台提交的数据有：父部门的name，新家部门的name，因此我们需要在service中处理
		deptService.saveOrUpdate(model);
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
		deptService.delete(Dept.class, ids);
		// 3、返回页面
		return "alist"; // 删除成功后返回到列表页面
	}

	/**
	 * 进入修改页面
	 */
	public String toupdate() throws Exception {
		Dept dept = deptService.get(Dept.class, model.getId());
		// 放入值栈顶
		super.push(dept);
		saveDeptList();
		return "toupdate";
	}

	/**
	 * 修改
	 */
	public String update() throws Exception {
		// 找到要修改的对象
		Dept obj = deptService.get(Dept.class, model.getId());
		// 设置要修改的属性
		obj.setParent(model.getParent());
		obj.setDeptName(model.getDeptName());

		deptService.saveOrUpdate(obj);
		return "alist";
	}

	public void saveDeptList() {
		// 1、查询所有部门
		List<Dept> deptList = deptService.find("from Dept where state = 1", Dept.class, null);
		// 2、将部门集合存入值栈,
		// 注意 :对应的应该怎么取值,在contextMap中使用#
		ActionContext.getContext().put("deptList", deptList);
	}

}
