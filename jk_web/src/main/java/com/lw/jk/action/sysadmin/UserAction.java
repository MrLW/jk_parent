package com.lw.jk.action.sysadmin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lw.jk.action.BaseAction;
import com.lw.jk.pojo.Dept;
import com.lw.jk.pojo.Role;
import com.lw.jk.pojo.User;
import com.lw.jk.service.DeptService;
import com.lw.jk.service.RoleService;
import com.lw.jk.service.UserService;
import com.lw.jk.utils.Page;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 部门管理
 * @author lw
 */
public class UserAction extends BaseAction implements ModelDriven<User> {

	private User model = new User();
	// 分页
	private Page page = new Page<>();

	private UserService userService;
	private DeptService deptService;
	private RoleService roleService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@Override
	public User getModel() {
		return model;
	}

	public Page getPage() {
		return page;
	}

	/**
	 * 分页查询
	 */
	public String list() throws Exception {
		// HQL查询
		userService.findPage("from User", page, User.class, null);
		// 设置分页URL
		page.setUrl("userAction_list");
		// 将page对象压入栈顶
		super.push(page);
		return "list";
	}

	/**
	 * 查看
	 */
	public String toview() throws Exception {
		// 根據ID查询对象
		User dept = userService.get(User.class, model.getId());
		// 存入值栈
		super.push(dept);
		return "toview";
	}

	/**
	 * 新增
	 */
	public String tocreate() throws Exception {
		// 选择部门
		List<Dept> deptList = deptService.find("from Dept where state = 1", Dept.class, null);
		super.put("deptList", deptList);
		// 选择领导
		List<User> userList = userService.find("from User where state = 1 ", User.class, null);
		super.put("userList", userList);
		// 3、跳转页面
		return "tocreate";
	}

	/**
	 * 新增部门
	 */
	public String insert() throws Exception {
		// 清除id
		model.setId(null);
		// 从前台提交的数据有：父部门的name，新家部门的name，因此我们需要在service中处理
		userService.saveOrUpdate(model);
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
		userService.delete(User.class, ids);
		// 3、返回页面
		return "alist"; // 删除成功后返回到列表页面
	}

	/**
	 * 进入修改页面
	 */
	public String toupdate() throws Exception {
		User dept = userService.get(User.class, model.getId());
		// 放入值栈顶
		super.push(dept);
		List<Dept> deptList = deptService.find("from Dept where state = 1 ", Dept.class, null);
		// 将部门列表存入contextMap
		super.put("deptList", deptList);

		return "toupdate";
	}

	/**
	 * 修改
	 */
	public String update() throws Exception {
		// 找到要修改的对象
		User obj = userService.get(User.class, model.getId());
		obj.setUserName(model.getUserName());
		obj.setDept(model.getDept());
		obj.setState(model.getState());
		userService.saveOrUpdate(obj);
		return "alist";
	}

	/**
	 * 进入到角色列表页面
	 */
	public String torole() throws Exception {
		// 1、根据Id获取用户
		User user = userService.get(User.class, model.getId());
		// 将user存入栈顶
		super.push(user);
		// 2、查找用户所对应的权限
		Set<Role> roles = user.getRole();
		StringBuilder sb = new StringBuilder();
		for (Role role : roles) {
			sb.append(role.getName());
		}
		// 存入值栈
		super.put("userRoleStr", sb.toString());
		// 3、查找角色列表
		List<Role> roleList = roleService.find("from Role", Role.class, null);
		// 存入栈顶
		super.put("roleList", roleList);
		// 4、跳转页面
		return "torole";
	}

	/**
	 * 进行添加角色
	 */
	public String role() throws Exception {
		// 1、根据ID查找对应的用户
		User user = userService.get(User.class, model.getId());
		Set<Role> roleSet = new HashSet<>();
		// 2、获取对应的角色列表
		for (String roleId : roleIds) {
			// 根据roleId查找角色
			Role role = roleService.get(Role.class, roleId);
			roleSet.add(role);
		}
		user.setRole(roleSet);
		// 3、执行更新操作
		userService.saveOrUpdate(user);
		// 4、返回到列表页面
		return "alist";
	}

	private String[] roleIds;

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	

}
