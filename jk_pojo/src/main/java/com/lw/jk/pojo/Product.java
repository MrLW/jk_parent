package com.lw.jk.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @Description:	Product
 * @Author:			
 * @Company:		
 * @CreateDate:		2017-5-1 20:33:12
 */

public class Product extends BasePojo {
	private static final long serialVersionUID = 1L;

	private String id;	  	
	private String productNo;			//
	private String productImage;			//
	private String description;			//
	private String factoryId;			//
	private String factoryName;			//
	private Double price;			//
	private Double sizeLenght;			//
	private Double sizeWidth;			//
	private Double sizeHeight;			//
	private String color;			//
	private String packing;			//
	private String packingUnit;			//
	private Double type20;			//
	private Double type40;			//
	private Double type40hc;			//
	private Integer qty;			//
	private Double cbm;			//
	private Double mpsizeLenght;			//
	private Double mpsizeWidth;			//
	private Double mpsizeHeight;			//
	private String remark;			//
	private Date inputTime;			//
	private String createBy;			//
	private String createDept;			//
	private Date createTime;			//

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getProductNo() {
		return this.productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}	
	
	public String getProductImage() {
		return this.productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}	
	
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
	
	public String getFactoryId() {
		return this.factoryId;
	}
	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}	
	
	public String getFactoryName() {
		return this.factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}	
	
	public Double getPrice() {
		return this.price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}	
	
	public Double getSizeLenght() {
		return this.sizeLenght;
	}
	public void setSizeLenght(Double sizeLenght) {
		this.sizeLenght = sizeLenght;
	}	
	
	public Double getSizeWidth() {
		return this.sizeWidth;
	}
	public void setSizeWidth(Double sizeWidth) {
		this.sizeWidth = sizeWidth;
	}	
	
	public Double getSizeHeight() {
		return this.sizeHeight;
	}
	public void setSizeHeight(Double sizeHeight) {
		this.sizeHeight = sizeHeight;
	}	
	
	public String getColor() {
		return this.color;
	}
	public void setColor(String color) {
		this.color = color;
	}	
	
	public String getPacking() {
		return this.packing;
	}
	public void setPacking(String packing) {
		this.packing = packing;
	}	
	
	public String getPackingUnit() {
		return this.packingUnit;
	}
	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}	
	
	public Double getType20() {
		return this.type20;
	}
	public void setType20(Double type20) {
		this.type20 = type20;
	}	
	
	public Double getType40() {
		return this.type40;
	}
	public void setType40(Double type40) {
		this.type40 = type40;
	}	
	
	public Double getType40hc() {
		return this.type40hc;
	}
	public void setType40hc(Double type40hc) {
		this.type40hc = type40hc;
	}	
	
	public Integer getQty() {
		return this.qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}	
	
	public Double getCbm() {
		return this.cbm;
	}
	public void setCbm(Double cbm) {
		this.cbm = cbm;
	}	
	
	public Double getMpsizeLenght() {
		return this.mpsizeLenght;
	}
	public void setMpsizeLenght(Double mpsizeLenght) {
		this.mpsizeLenght = mpsizeLenght;
	}	
	
	public Double getMpsizeWidth() {
		return this.mpsizeWidth;
	}
	public void setMpsizeWidth(Double mpsizeWidth) {
		this.mpsizeWidth = mpsizeWidth;
	}	
	
	public Double getMpsizeHeight() {
		return this.mpsizeHeight;
	}
	public void setMpsizeHeight(Double mpsizeHeight) {
		this.mpsizeHeight = mpsizeHeight;
	}	
	
	public String getRemark() {
		return this.remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}	
	
	public Date getInputTime() {
		return this.inputTime;
	}
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}	
	
	
	
}
