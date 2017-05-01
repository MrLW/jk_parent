package com.lw.jk.pojo;

import java.util.HashSet;
import java.util.Set;

public class User extends BasePojo {

	private String id; // 用户Id
	private Dept dept; // 用户所在部门 关系： 多对一
	private String userName; // 用户名
	private String password;
	private Integer state;
	private Set<Role> roles = new HashSet<>(); 
	// user对象持有userinfo的引用
	private UserInfo userInfo ;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	
	public Set<Role> getRole() {
		return roles;
	}
	public void setRole(Set<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", dept=" + dept + ", userName=" + userName + ", password=" + password + ", state="
				+ state + ", role=" + roles + ", userInfo=" + userInfo + "]";
	}
	
	
	
}