package com.lw.jk.action.cargo;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.lw.jk.action.BaseAction;
import com.lw.jk.pojo.Contract;
import com.lw.jk.pojo.Export;
import com.lw.jk.pojo.ExportProduct;
import com.lw.jk.pojo.User;
import com.lw.jk.service.ContractService;
import com.lw.jk.service.ExportProductService;
import com.lw.jk.service.ExportService;
import com.lw.jk.utils.Page;
import com.lw.jk.utils.UtilFuns;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.export.webservice.EpService;

/**
 * 购销合同action
 * 
 * @author lw
 */
public class ExportAction extends BaseAction implements ModelDriven<Export> {

	private Export model = new Export();
	// 分页
	private Page page = new Page<>();

	private ExportService exportService;

	private ContractService contractService;

	private ExportProductService exportProductService;
	
	private EpService epService ;
	
	public void setEpService(EpService epService) {
		this.epService = epService;
	}

	public void setExportService(ExportService exportService) {
		this.exportService = exportService;
	}

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}

	public void setExportProductService(ExportProductService exportProductService) {
		this.exportProductService = exportProductService;
	}

	@Override
	public Export getModel() {
		return model;
	}

	public Page getPage() {
		return page;
	}

	/**
	 * 查询状态为1记录
	 */
	public String contractList() throws Exception {
		// 查询状态为1的购销合同
		String hql = "from Contract where state = 1 ";
		// 分页查询
		contractService.findPage(hql, page, Contract.class, null);
		page.setUrl("exportAction_contractList");
		// 放入值栈
		super.push(page);
		return "contractList";
	}

	/**
	 * 查看
	 */
	public String toview() throws Exception {
		// 根據ID查询对象
		Export dept = exportService.get(Export.class, model.getId());
		// 存入值栈
		super.push(dept);
		return "toview";
	}

	/**
	 */
	public String tocreate() throws Exception {
		System.out.println("tocreate");
		return "tocreate";
	}

	/**
	 * 有一个bug，从前台传递过来的数据总是携带一个ID 新增部门
	 */
	public String insert() throws Exception {
		// 1.加入细粒度权限控制的数据
		User user = super.getCurrentUser();
		model.setCreateBy(user.getId());// 设置创建者的id
		model.setCreateDept(user.getDept().getId());// 设置创建者所在部门的id

		// 2、调用方法
		exportService.saveOrUpdate(model);

		return contractList();
	}

	/**
	 * 删除 因为可能删除的不只是1条数据，因此需要注意： 如果id是string类型的，则id属性会使用逗号分隔符
	 * 如果id是integer类型的，则只会取最后一条id值
	 */
	public String delete() throws Exception {
		// 1、需要将要删除的部门分割开
		String[] ids = model.getId().split(", ");
		// 2、调用service的方法一次性删除
		exportService.delete(Export.class, ids);
		// 3、返回页面
		return "alist"; // 删除成功后返回到列表页面
	}

	/**
	 * 进入修改页面
	 */
	public String toupdate() throws Exception {
		// 1、根据ID查询报运单
		Export export = exportService.get(Export.class, model.getId());
		// 2、放入值栈顶
		super.push(export);

		StringBuilder sb = new StringBuilder();
		// 获取关联的ExportProduct
		Set<ExportProduct> eps = export.getExportProducts();
		// 通过拼接字符串的方式传入前台直接调用前台的方法
		for (ExportProduct ep : eps) {
			sb.append("addTRRecord(\"mRecordTable\", \"").append(ep.getId());
			sb.append("\", \"").append(ep.getProductNo());
			sb.append("\", \"").append(UtilFuns.convertNull(ep.getCnumber()));
			sb.append("\", \"").append(UtilFuns.convertNull(ep.getGrossWeight()));
			sb.append("\", \"").append(UtilFuns.convertNull(ep.getNetWeight()));
			sb.append("\", \"").append(UtilFuns.convertNull(ep.getSizeLength()));
			sb.append("\", \"").append(UtilFuns.convertNull(ep.getSizeWidth()));
			sb.append("\", \"").append(UtilFuns.convertNull(ep.getSizeHeight()));
			sb.append("\", \"").append(UtilFuns.convertNull(ep.getExPrice()));
			sb.append("\", \"").append(UtilFuns.convertNull(ep.getTax())).append("\");");
		}
		// 存入值栈
		super.put("mRecordData", sb.toString());

		return "toupdate";
	}

	/**
	 * 修改
	 */
	public String update() throws Exception {
		// 根据id查询报运单
		Export export = exportService.get(Export.class, model.getId());
		// 设置要修改的属性
		export.setInputDate(model.getInputDate());
		export.setLcno(model.getLcno());
		export.setConsignee(model.getConsignee());
		export.setShipmentPort(model.getShipmentPort());
		export.setDestinationPort(model.getDestinationPort());
		export.setTransportMode(model.getTransportMode());
		export.setPriceCondition(model.getPriceCondition());
		export.setMarks(model.getMarks());// 唛头是指具有一定格式的备注信息
		export.setRemark(model.getRemark());

		Set<ExportProduct> eps = new HashSet<ExportProduct>();// 商品列表

		for (int i = 0; i < mr_id.length; i++) {
			ExportProduct ep = exportProductService.get(ExportProduct.class, mr_id[i]);
			if ("1".equals(mr_changed[i])) {
				ep.setCnumber(mr_cnumber[i]);
				ep.setGrossWeight(mr_grossWeight[i]);
				ep.setNetWeight(mr_netWeight[i]);
				ep.setSizeLength(mr_sizeLength[i]);
				ep.setSizeWidth(mr_sizeWidth[i]);
				ep.setSizeHeight(mr_sizeHeight[i]);
				ep.setExPrice(mr_exPrice[i]);
				ep.setTax(mr_tax[i]);
			}
			eps.add(ep) ;
		}
		// 设置关系
		export.setExportProducts(eps);
		// 保存
		exportService.saveOrUpdate(export);
		return "alist";
	}

	/**
	 * 出口报运
	 */
	public String list() {
		String hql = "from Export ";

		exportService.findPage(hql, page, Export.class, null);
		// 设置分页URL
		page.setUrl("exportAction_list");
		// 将page对象存入栈顶
		super.push(page);
		return "list";
	}
	
	/**
	 *  电子报运
	 */
	public String export() throws Exception {
		// 1、根据id查询报运单对象
		Export export = exportService.get(Export.class, model.getId());
		// 2、将报运单对象转换成为json对象
		String json = JSON.toJSONString(export);
		System.out.println("json:" + json);
		// 3、调用ws将json对象传递报运平台
		String result = epService.exportE(json);
		// 4、处理报运平台响应的结果
		System.out.println("result:" + result);
		// 解析返回的json
		Export resultExport = JSON.parseObject(result, Export.class);
		exportService.updateE(resultExport);
		return "alist";
	}

	/**
	 * 提交
	 */
	public String submit() {
		String[] ids = model.getId().split(", ");
		exportService.changeState(ids, 1);
		;
		return "alist";
	}

	/**
	 * 取消
	 */
	public String cancel() {
		String[] ids = model.getId().split(", ");
		exportService.changeState(ids, 0);
		;
		return "alist";
	}

	private String mr_changed[];
	private String mr_id[];
	private Integer mr_cnumber[];
	private Double mr_grossWeight[];
	private Double mr_netWeight[];
	private Double mr_sizeLength[];
	private Double mr_sizeWidth[];
	private Double mr_sizeHeight[];
	private Double mr_exPrice[];
	private Double mr_tax[];
	private String[] mr_orderNo; 

	public String[] getMr_changed() {
		return mr_changed;
	}

	public void setMr_changed(String[] mr_changed) {
		this.mr_changed = mr_changed;
	}

	public String[] getMr_id() {
		return mr_id;
	}

	public void setMr_id(String[] mr_id) {
		this.mr_id = mr_id;
	}

	public Integer[] getMr_cnumber() {
		return mr_cnumber;
	}

	public void setMr_cnumber(Integer[] mr_cnumber) {
		this.mr_cnumber = mr_cnumber;
	}

	public Double[] getMr_grossWeight() {
		return mr_grossWeight;
	}

	public void setMr_grossWeight(Double[] mr_grossWeight) {
		this.mr_grossWeight = mr_grossWeight;
	}

	public Double[] getMr_netWeight() {
		return mr_netWeight;
	}

	public void setMr_netWeight(Double[] mr_netWeight) {
		this.mr_netWeight = mr_netWeight;
	}

	public Double[] getMr_sizeLength() {
		return mr_sizeLength;
	}

	public void setMr_sizeLength(Double[] mr_sizeLength) {
		this.mr_sizeLength = mr_sizeLength;
	}

	public Double[] getMr_sizeWidth() {
		return mr_sizeWidth;
	}

	public void setMr_sizeWidth(Double[] mr_sizeWidth) {
		this.mr_sizeWidth = mr_sizeWidth;
	}

	public Double[] getMr_sizeHeight() {
		return mr_sizeHeight;
	}

	public void setMr_sizeHeight(Double[] mr_sizeHeight) {
		this.mr_sizeHeight = mr_sizeHeight;
	}

	public Double[] getMr_exPrice() {
		return mr_exPrice;
	}

	public void setMr_exPrice(Double[] mr_exPrice) {
		this.mr_exPrice = mr_exPrice;
	}

	public Double[] getMr_tax() {
		return mr_tax;
	}

	public void setMr_tax(Double[] mr_tax) {
		this.mr_tax = mr_tax;
	}

	public String[] getMr_orderNo() {
		return mr_orderNo;
	}

	public void setMr_orderNo(String[] mr_orderNo) {
		this.mr_orderNo = mr_orderNo;
	}
	
	

}
