package jk_web;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class POITest {

	public static void main(String[] args) {
		try {
			// 1、创建工作普
			Workbook wb = new HSSFWorkbook();
			// 2、创建工作表
			Sheet sheet = wb.createSheet();
			// 3、创建row
			Row row = sheet.createRow(3);
			// 4、创建单元格
			Cell cell = row.createCell(3);
			// 5、设置内容
			cell.setCellValue("Hello POI");
			// 6、设置style
			CellStyle cellStyle = wb.createCellStyle();
			Font font = wb.createFont();
			font.setBold(true);
			font.setItalic(true);
			cellStyle.setFont(font);
			cell.setCellStyle(cellStyle);
			// 7、保存并且关闭
			OutputStream os = new FileOutputStream("D:\\e.xls");
			
			wb.write(os);
			
			
			os.close();
			wb.close(); 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
