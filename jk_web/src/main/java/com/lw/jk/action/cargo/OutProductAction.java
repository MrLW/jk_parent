package com.lw.jk.action.cargo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;

import com.lw.jk.action.BaseAction;
import com.lw.jk.pojo.ContractProduct;
import com.lw.jk.service.ContractProductService;
import com.lw.jk.service.ContractService;
import com.lw.jk.utils.UtilFuns;

public class OutProductAction extends BaseAction {

	// 接受页面上的船期
	private String inputDate;
	private ContractService contractService;
	private ContractProductService contractProductService;
	// 输出变量流, 在struts.xml中使用
	private InputStream inputStream;
	// 文件名,在struts.xml中使用
	private String fileName;

	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}

	public void setContractProductService(ContractProductService contractProductService) {
		this.contractProductService = contractProductService;
	}

	public InputStream getInputStream() {
		System.out.println("getInputStream execute .....");
		return inputStream;
	}

	public String getFileName() {
		System.out.println("getFileName execute ....");
		return fileName;
	}

	/**
	 * 进入到打印界面
	 */
	public String toedit() throws Exception {
		return "toedit";
	}

	/**
	 * 通过模板打印出货表
	 */
	public String print() throws Exception {
		// 通用变量
		int rowNo = 0, cellNo = 1;
		Row nRow = null;
		Cell nCell = null;

		// 1.读取工作簿
		String path = ServletActionContext.getServletContext().getRealPath("/") + "/make/xlsprint/tOUTPRODUCT.xls";
		System.out.println(path);

		InputStream is = new FileInputStream(path);
		Workbook wb = new HSSFWorkbook(is);

		// 2.读取工作表
		Sheet sheet = wb.getSheetAt(0);

		cellNo = 1;// 重置

		// 3.创建行对象
		// =========================================大标题=============================
		nRow = sheet.getRow(rowNo++);// 读取行对象
		nCell = nRow.getCell(cellNo);
		// 设置单元格的内容
		nCell.setCellValue(inputDate.replace("-0", "-").replace("-", "年") + "月份出货表");// 2015-01
																						// 2015-12

		// =======================================小标题=================================
		rowNo++;
		// =======================================数据输出=================================================
		nRow = sheet.getRow(rowNo);// 读取第三行
		CellStyle customCellStyle = nRow.getCell(cellNo++).getCellStyle();
		CellStyle orderNoCellStyle = nRow.getCell(cellNo++).getCellStyle();
		CellStyle productNoCellStyle = nRow.getCell(cellNo++).getCellStyle();
		CellStyle cNumberCellStyle = nRow.getCell(cellNo++).getCellStyle();
		CellStyle factoryCellStyle = nRow.getCell(cellNo++).getCellStyle();
		CellStyle deliveryPeriodCellStyle = nRow.getCell(cellNo++).getCellStyle();
		CellStyle shipTimeCellStyle = nRow.getCell(cellNo++).getCellStyle();
		CellStyle tradeTermsCellStyle = nRow.getCell(cellNo++).getCellStyle();

		String hql = "from ContractProduct ";
		List<ContractProduct> list = contractProductService.find(hql, ContractProduct.class, null);// 查询出符合指定船期的货物列表

		for (ContractProduct cp : list) {
			nRow = sheet.createRow(rowNo++);// 产生数据行
			nRow.setHeightInPoints(24);// 设置行高

			cellNo = 1;
			nCell = nRow.createCell(cellNo++);// 产生单元格对象
			nCell.setCellValue(cp.getContract().getCustomName());// 客户名称
			nCell.setCellStyle(customCellStyle);// 设置文本样式

			nCell = nRow.createCell(cellNo++);// 产生单元格对象
			nCell.setCellValue(cp.getContract().getContractNo());// 订单号--- 合同号
			nCell.setCellStyle(orderNoCellStyle);// 设置文本样式

			nCell = nRow.createCell(cellNo++);// 产生单元格对象
			nCell.setCellValue(cp.getProductNo()); // 货号
			nCell.setCellStyle(productNoCellStyle);// 设置文本样式

			nCell = nRow.createCell(cellNo++);// 产生单元格对象
			nCell.setCellValue(cp.getCnumber());// 数量
			nCell.setCellStyle(cNumberCellStyle);// 设置文本样式

			nCell = nRow.createCell(cellNo++);// 产生单元格对象
			nCell.setCellValue(cp.getFactoryName());// 工厂名
			nCell.setCellStyle(factoryCellStyle);// 设置文本样式

			nCell = nRow.createCell(cellNo++);// 产生单元格对象
			nCell.setCellValue(UtilFuns.dateTimeFormat(cp.getContract().getDeliveryPeriod()));// 交期
			nCell.setCellStyle(deliveryPeriodCellStyle);// 设置文本样式

			nCell = nRow.createCell(cellNo++);// 产生单元格对象
			nCell.setCellValue(UtilFuns.dateTimeFormat(cp.getContract().getShipTime()));// 船期
			nCell.setCellStyle(shipTimeCellStyle);// 设置文本样式

			nCell = nRow.createCell(cellNo++);// 产生单元格对象
			nCell.setCellValue(cp.getContract().getTradeTerms());// 贸易条款
			nCell.setCellStyle(tradeTermsCellStyle);// 设置文本样式

		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		wb.write(baos);

		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

		inputStream = bais;
		// 设置文件名,解决中文乱码
		fileName = new String("哈哈111.xls".getBytes("gb2312"), "ISO8859-1");
		return "print";
	}

}
