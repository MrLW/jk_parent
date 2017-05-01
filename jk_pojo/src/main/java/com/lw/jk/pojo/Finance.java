package com.lw.jk.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @Description:	Finance
 * @Author:			
 * @Company:		
 * @CreateDate:		2017-4-30 19:46:33
 */

public class Finance implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;	  	
	private Date inputDate;			//
	private String inputBy;			//
	private Integer state;			//
	private String createBy;			//
	private String createDept;			//
	private Date createTime;			//

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public Date getInputDate() {
		return this.inputDate;
	}
	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}	
	
	public String getInputBy() {
		return this.inputBy;
	}
	public void setInputBy(String inputBy) {
		this.inputBy = inputBy;
	}	
	
	public Integer getState() {
		return this.state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateDept() {
		return createDept;
	}
	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}	
	
	
	
}
