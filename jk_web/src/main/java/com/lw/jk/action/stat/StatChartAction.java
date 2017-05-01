package com.lw.jk.action.stat;

import java.io.FileNotFoundException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.lw.jk.action.BaseAction;
import com.lw.jk.service.StatChartService;
import com.lw.jk.utils.file.FileUtil;

/**
 * 图表操作
 * 
 * @author lw
 */
public class StatChartAction extends BaseAction {

	private StatChartService statChartService;

	public void setStatChartService(StatChartService statChartService) {
		this.statChartService = statChartService;
	}

	// 拼接json
	public String factorysale() throws Exception {

		// 1、查找集合
		List<String> list = statChartService.executeSQL(
				"select factory_name,sum(amount) samount from contract_product_c group by factory_name order by samount desc");

		// 2.组织符合要求的json数据
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		/**
		 * { "country": "USA", "visits": 4025, "color": "#FF0F00" }
		 */
		String colors[] = { "#FF0F00", "#FF6600", "#FF9E01", "#FCD202", "#F8FF01", "#B0DE09", "#04D215", "#0D52D1",
				"#2A0CD0", "#8A0CCF", "#CD0D74", "#754DEB" };
		int j = 0;
		for (int i = 0; i < list.size(); i++) {
			sb.append("{").append("\"factory\":\"").append(list.get(i)).append("\",").append("\"amount\":\"")
					.append(list.get((++i))).append("\",").append("\"color\":\"").append(colors[j++]).append("\"")
					.append("}").append(",");
			if (j >= colors.length) {
				j = 0;
			}
		}
		sb.delete(sb.length() - 1, sb.length()); // 删除最后一个,

		sb.append("]");

		// 3.将json数据放入值栈中
		super.put("result", sb.toString());

		// 4.返回结果

		return "column3D";
	}

	/**
	 * 厂家的销量排名 使用旧版本xml实现
	 */
	public String factorysale1() throws Exception {
		// 1、查找集合
		List<String> list = statChartService.executeSQL(
				"select factory_name,sum(amount) samount from contract_product_c group by factory_name order by samount desc");
		// 2、组织xml数据
		String content = getStringDataFromFactorySaleList(list);
		// 3、写入xml
		writeXML("stat\\chart\\factorysale\\data.xml", content);

		return "factorysale";
	}

	/**
	 * 产品销售排行
	 */
	public String productsale() throws Exception {
		List<String> list = statChartService.executeSQL(
				"SELECT * FROM ( SELECT product_no,SUM(amount) samount FROM contract_product_c GROUP BY product_no ORDER BY samount DESC ) b LIMIT 15");
		String content = getStringDataFromProductSaleList(list);
		// 3、写入xml
		writeXML("stat\\chart\\productsale\\data.xml", content);

		return "productsale";
	}

	/**
	 * 系统压力 图
	 */
	public String onlineinfo() throws Exception {
		// 1、获取数据
		List list = statChartService.executeSQL(
				"SELECT a.a1, IFNULL(b.c,0) FROM (SELECT * FROM online_info_t) a LEFT JOIN (SELECT DATE_FORMAT(LOGIN_TIME,'%m') a1, COUNT(*) c FROM login_log_p GROUP BY  DATE_FORMAT(LOGIN_TIME,'%m') ORDER BY a1) b ON (a.a1=b.a1) ORDER BY a.a1");
		// 2、解析数据
		String content = getStringDataFromProductSaleList(list);
		// 3、写入xml
		writeXML("/stat/chart/onlineinfo/data.xml", content);
		return "onlineinfo";
	}

	/**
	 * 将产品集合转换成XML
	 * 
	 * @param list
	 * @return
	 */
	private String getStringDataFromProductSaleList(List<String> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><chart><series>");
		int j = 0;
		for (int i = 0; i < list.size(); i++) {
			sb.append("<value xid=\"" + (j++) + "\">" + list.get(i) + "</value>");
			i++;
		}
		sb.append("</series><graphs><graph gid=\"30\" color=\"#FFCC00\" gradient_fill_colors=\"#111111, #1A897C\">");
		// 重置j
		j = 0;
		for (int i = 0; i < list.size(); i++) {
			i++;
			sb.append("<value xid=\"" + (j++) + "\" description=\"\" url=\"\">" + list.get(i) + "</value>");
		}
		sb.append("</graph></graphs>");
		sb.append(
				"<labels><label lid=\"0\"><x>0</x><y>20</y><rotate></rotate><width></width><align>center</align><text_color></text_color><text_size></text_size><text><![CDATA[<b>产品销量排名</b>]]></text></label></labels>");
		sb.append("</chart>");
		return sb.toString();
	}

	/**
	 * 拼接data.xml需要的字符串
	 * 
	 * @param list
	 * @return
	 */
	private String getStringDataFromFactorySaleList(List<String> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<pie>");
		// 开始填充数据
		for (int i = 0; i < list.size(); i++) {
			sb.append("<slice title=\"" + list.get(i) + "\">" + list.get(++i) + "</slice>");
		}
		sb.append("</pie>");
		return sb.toString();
	}

	/**
	 * 写XML到文件
	 * 
	 * @param fileName
	 * @param content
	 * @throws FileNotFoundException
	 */
	private void writeXML(String fileName, String content) throws Exception {
		// 使用工具类写入data.xml
		FileUtil fileUtil = new FileUtil();
		String sPath = ServletActionContext.getServletContext().getRealPath("/");
		fileUtil.createTxt(sPath, fileName, content, "utf-8");
	}
}
