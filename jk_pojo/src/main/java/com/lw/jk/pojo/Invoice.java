package com.lw.jk.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @Description:	Invoice
 * @Author:			
 * @Company:		
 * @CreateDate:		2017-4-30 16:15:15
 */

public class Invoice extends BasePojo {
	private static final long serialVersionUID = 1L;

	private String id;	  	
	private String scNo;			//
	private String blNo;			//
	private String tradeTerms;			//
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
	
	
	public String getScNo() {
		return this.scNo;
	}
	public void setScNo(String scNo) {
		this.scNo = scNo;
	}	
	
	public String getBlNo() {
		return this.blNo;
	}
	public void setBlNo(String blNo) {
		this.blNo = blNo;
	}	
	
	public String getTradeTerms() {
		return this.tradeTerms;
	}
	public void setTradeTerms(String tradeTerms) {
		this.tradeTerms = tradeTerms;
	}	
	
	public Integer getState() {
		return this.state;
	}
	public void setState(Integer state) {
		this.state = state;
	}	
	
	
	
}
