package com.lw.jk.pojo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 用于返回给前台的pojo
 * 
 * @author lw
 */
public class TreeResult implements Serializable {
	// 指定序列化的顺序
	@JSONField(ordinal = 1)
	private String id; // 当前节点的ID
	@JSONField(ordinal = 2)
	private String pid; // 当前节点 的父节点的ID
	@JSONField(ordinal = 3)
	private String name; // 当前节点的名字
	@JSONField(ordinal = 4)
	private String checked; // 当前节点是否被选中

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	@Override
	public String toString() {
		return "TreeResult [id=" + id + ", pid=" + pid + ", name=" + name + ", checked=" + checked + "]";
	}

}
