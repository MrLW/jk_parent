package com.lw.jk.action.sysadmin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lw.jk.action.BaseAction;
import com.lw.jk.exception.SysException;
import com.lw.jk.pojo.Dept;
import com.lw.jk.pojo.Module;
import com.lw.jk.pojo.Role;
import com.lw.jk.pojo.TreeResult;
import com.lw.jk.service.DeptService;
import com.lw.jk.service.ModuleService;
import com.lw.jk.service.RoleService;
import com.lw.jk.utils.Page;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 角色管理
 * 
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

	// 该key属性不作为json内容输出
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	@Override
	public Role getModel() {
		return model;
	}

	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public Page getPage() {
		return page;
	}

	/**
	 * 分页查询
	 */
	public String list() throws Exception {
		// 在list页面中清除模型中的id
		// model.setId(null);在moduleAction测试
		// 将page对象压入栈顶
		super.push(page);
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
			Role role = roleService.get(Role.class, model.getId());
			// 更改在列表中checkbox的状态
			roleService.setChecked(role, true);
			// 存入值栈
			super.push(role);
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
		model.setId(null);
		// 从前台提交的数据有：父部门的name，新家部门的name，因此我们需要在service中处理
		roleService.saveOrUpdate(model);
		// 清除所有选中
		roleService.setAllUnChecked();
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

	/**
	 * 生成角色管理中对应的权限json
	 * 
	 * @return
	 * @throws Exception
	 */
	public String roleModuleJsonStr() throws Exception {
		// 1、根据角色id得到角色
		Role role = roleService.get(Role.class, model.getId());
		// 2、加载当前角色的模块列表
		Set<Module> moduleSet = role.getModules();
		List<Module> moduleList = moduleService.find("from Module", Module.class, null);
		int size = moduleList.size();
		// 3、这里需要特定格式的json,方法1：拼接json
		// 方法2：创建一个特定的pojo,然后使用fastjson来解析
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		// 方法一、使用拼接字符串的方式
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

		// 方法二、使用fastjson解析的方式
		// 创建集合
		List<TreeResult> resultList = new ArrayList<>();
		for (Module module : moduleList) {
			// 1、创建TreeResult对象
			TreeResult tr = new TreeResult();
			tr.setId(module.getId());
			tr.setName(module.getName());
			tr.setPid(module.getParentId());
			tr.setChecked(moduleSet.contains(module) ? "true" : "false");
			resultList.add(tr);
		}
		// 使用fastjson解析
		String json = JSON.toJSONString(resultList, SerializerFeature.WriteMapNullValue);
		// 将json中的null转换成为字符串"null"
		json = json.replaceAll("null", "\"null\"");
		// 4、使用response输出
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");

		response.getWriter().write(sb.toString());// 方法一

		System.out.println("json1:" + json);
		System.out.println("json2:" + sb.toString());
		/**
		 * json1:[{"id":"1","pid":"null","name":"系统首页","checked":"true"},{"id":"101","pid":"1","name":"我的留言板","checked":"true"},{"id":"102","pid":"1","name":"我的代办任务","checked":"true"},{"id":"103","pid":"1","name":"请假单管理","checked":"true"},{"id":"2","pid":"null","name":"货运管理","checked":"true"},{"id":"201","pid":"2","name":"购销合同","checked":"true"},{"id":"202","pid":"2","name":"出货表","checked":"true"},{"id":"203","pid":"2","name":"合同管理","checked":"true"},{"id":"204","pid":"2","name":"出口报运","checked":"true"},{"id":"205","pid":"2","name":"装箱管理","checked":"true"},{"id":"206","pid":"2","name":"委托管理","checked":"true"},{"id":"207","pid":"2","name":"发票管理","checked":"true"},{"id":"208","pid":"2","name":"财务管理","checked":"true"},{"id":"3","pid":"null","name":"统计分析","checked":"false"},{"id":"301","pid":"3","name":"生产厂家销售情况","checked":"false"},{"id":"302","pid":"3","name":"产品销售排行","checked":"false"},{"id":"303","pid":"3","name":"系统访问压力图","checked":"false"},{"id":"4","pid":"null","name":"基础信息","checked":"false"},{"id":"401","pid":"4","name":"产品信息","checked":"false"},{"id":"402","pid":"4","name":"厂家信息","checked":"false"},{"id":"4028827c4fbbba20014fbbbc80570000","pid":"null","name":"44","checked":"false"},{"id":"5","pid":"null","name":"系统管理","checked":"false"},{"id":"501","pid":"5","name":"部门管理","checked":"false"},{"id":"502","pid":"5","name":"用户管理","checked":"false"},{"id":"503","pid":"5","name":"角色管理","checked":"false"},{"id":"504","pid":"5","name":"模块管理","checked":"false"},{"id":"6","pid":"null","name":"流程管理","checked":"false"},{"id":"601","pid":"6","name":"部署流程","checked":"false"},{"id":"602","pid":"6","name":"查询流程","checked":"false"},{"id":"603","pid":"6","name":"添加采购单","checked":"false"},{"id":"604","pid":"6","name":"查询采购单","checked":"false"}]
		 * json2:[{"id":"1","pId":"null","name":"系统首页","checked":"true"},{"id":"101","pId":"1","name":"我的留言板","checked":"true"},{"id":"102","pId":"1","name":"我的代办任务","checked":"true"},{"id":"103","pId":"1","name":"请假单管理","checked":"true"},{"id":"2","pId":"null","name":"货运管理","checked":"true"},{"id":"201","pId":"2","name":"购销合同","checked":"true"},{"id":"202","pId":"2","name":"出货表","checked":"true"},{"id":"203","pId":"2","name":"合同管理","checked":"true"},{"id":"204","pId":"2","name":"出口报运","checked":"true"},{"id":"205","pId":"2","name":"装箱管理","checked":"true"},{"id":"206","pId":"2","name":"委托管理","checked":"true"},{"id":"207","pId":"2","name":"发票管理","checked":"true"},{"id":"208","pId":"2","name":"财务管理","checked":"true"},{"id":"3","pId":"null","name":"统计分析","checked":"false"},{"id":"301","pId":"3","name":"生产厂家销售情况","checked":"false"},{"id":"302","pId":"3","name":"产品销售排行","checked":"false"},{"id":"303","pId":"3","name":"系统访问压力图","checked":"false"},{"id":"4","pId":"null","name":"基础信息","checked":"false"},{"id":"401","pId":"4","name":"产品信息","checked":"false"},{"id":"402","pId":"4","name":"厂家信息","checked":"false"},{"id":"4028827c4fbbba20014fbbbc80570000","pId":"null","name":"44","checked":"false"},{"id":"5","pId":"null","name":"系统管理","checked":"false"},{"id":"501","pId":"5","name":"部门管理","checked":"false"},{"id":"502","pId":"5","name":"用户管理","checked":"false"},{"id":"503","pId":"5","name":"角色管理","checked":"false"},{"id":"504","pId":"5","name":"模块管理","checked":"false"},{"id":"6","pId":"null","name":"流程管理","checked":"false"},{"id":"601","pId":"6","name":"部署流程","checked":"false"},{"id":"602","pId":"6","name":"查询流程","checked":"false"},{"id":"603","pId":"6","name":"添加采购单","checked":"false"},{"id":"604","pId":"6","name":"查询采购单","checked":"false"}]
		 * json1==json2:false
		 */
		// 问题：返回false,为什么不一样呢？两者输出的字符串一样
		System.out.println("json1==json2:" + json.equals(sb.toString()));

		return null;
		// return "jsondata";
	}

	/**
	 * 更改角色对应的权限
	 */
	public String module() throws Exception {
		String[] ids = moduleIds.split(",");
		// 1、根据角色ID查找对应角色
		Role role = roleService.get(Role.class, model.getId());
		// 创建集合存储module
		Set<Module> moduleSet = new HashSet<>();
		// 2、根据勾选的模块ID，查找对应模块
		if (ids != null && ids.length > 0) {
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

	// 要返回的json字符串
	private String json;

	public void setJson(String json) {
		this.json = json;
	}

	public String getJson() {
		return json;
	}
}
