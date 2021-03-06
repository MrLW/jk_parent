package com.lw.jk.pojo;

import java.util.HashSet;
import java.util.Set;

public class Role extends BasePojo{
	private String id;
	private Set<User> users = new HashSet<User>();// 角色与用户 多对多
	private String name;// 角色名
	private String remark;// 备注
	private String orderNo;// 排序号
	private Integer checked ;// 新添加的字段，在列表中这一行是否选中  1:选中,0未选中
	private Set<Module> modules = new HashSet<>() ; // 角色和模块多对多
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Set<Module> getModules() {
		return modules;
	}

	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}

	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}

}
