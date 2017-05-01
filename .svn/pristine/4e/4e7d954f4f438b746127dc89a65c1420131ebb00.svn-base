package com.lw.jk.action.sysadmin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.lw.jk.action.BaseAction;
import com.lw.jk.exception.SysException;
import com.lw.jk.pojo.Dept;
import com.lw.jk.pojo.Module;
import com.lw.jk.pojo.Role;
import com.lw.jk.service.DeptService;
import com.lw.jk.service.ModuleService;
import com.lw.jk.service.RoleService;
import com.lw.jk.utils.Page;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 部门管理
 * @author lw
 */
public class RoleAction extends BaseAction implements ModelDriven<Role> {

	private Role model = new Role();
	// 分页
	private Page page = new Page<>();

	private RoleService roleService;
	private DeptService deptService;
	private ModuleService moduleService;

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	@Override
	public Role getModel() {
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
		roleService.findPage("from Role", page, Role.class, null);
		// 设置分页URL
		page.setUrl("roleAction_list");
		return "list";
	}

	/**
	 * 查看
	 */
	public String toview() throws Exception {
		try {
			// 根據ID查询对象
			Role dept = roleService.get(Role.class, model.getId());
			// 存入值栈
			super.push(dept);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SysException("您的操作有误,请联系管理员");
		}
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
		// 从前台提交的数据有：父部门的name，新家部门的name，因此我们需要在service中处理
		roleService.saveOrUpdate(model);
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
		roleService.delete(Role.class, ids);
		// 3、返回页面
		return "alist"; // 删除成功后返回到列表页面
	}

	/**
	 * 进入修改页面
	 */
	public String toupdate() throws Exception {
		Role role = roleService.get(Role.class, model.getId());
		// 放入值栈顶
		super.push(role);

		return "toupdate";
	}

	/**
	 * 修改
	 */
	public String update() throws Exception {
		// 找到要修改的对象
		Role obj = roleService.get(Role.class, model.getId());
		// 设置要修改的属性
		obj.setName(model.getName());
		obj.setRemark(model.getRemark());
		roleService.saveOrUpdate(obj);
		return "alist";
	}

	/**
	 * 进入到角色管理的权限
	 */
	public String tomodule() throws Exception {
		// 1、根据ID查询当前角色对象
		Role role = roleService.get(Role.class, model.getId());
		// 2、将当前对象存入值栈
		super.push(role);
		// 3、跳转页面
		return "tomodule";
	}

	public String roleModuleJsonStr() throws Exception {
		// 1、根据角色id得到角色
		Role role = roleService.get(Role.class, model.getId());
		// 2、加载当前角色的模块列表
		Set<Module> moduleSet = role.getModules();
		List<Module> moduleList = moduleService.find("from Module", Module.class, null);
		int size = moduleList.size();
		// 3、拼接json
		StringBuilder sb = new StringBuilder();
		sb.append("[");

		for (Module module : moduleList) {
			size--;
			sb.append("{\"id\":\"").append(module.getId());
			sb.append("\",\"pId\":\"").append(module.getParentId());
			sb.append("\",\"name\":\"").append(module.getName());
			sb.append("\",\"checked\":\"");
			if (moduleSet.contains(module)) {
				sb.append("true");
			} else {
				sb.append("false");
			}
			sb.append("\"}");

			if (size > 0) {
				sb.append(",");
			}
		}
		sb.append("]");
		// 4、使用response输出
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		System.out.println("roleModuleJsonStr执行了");
		// 清除缓存
		response.getWriter().write(sb.toString());
		return null;
	}

	/**
	 * 更改角色对应的权限
	 */
	public String module() throws Exception {
		String[] ids = moduleIds.split(",");
		// 1、根据角色ID查找对应角色
		Role role = roleService.get(Role.class, model.getId());
		// 创建集合存储module
		Set<Module> moduleSet = new HashSet<>() ;
		// 2、根据勾选的模块ID，查找对应模块
		if(ids != null && ids.length > 0 ){
			for (String moduleId : ids) {
				Module module = moduleService.get(Module.class, moduleId);
				moduleSet.add(module);
			}
		}
		// 设置关系
		role.setModules(moduleSet);
		// 3、保存对应的模块
		roleService.saveOrUpdate(role);
		// 4、存储
		return "alist";
	}

	private String moduleIds;
	public void setModuleIds(String moduleIds) {
		this.moduleIds = moduleIds;
	}
}
