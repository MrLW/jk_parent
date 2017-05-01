package jk_web.mail;

import java.io.InputStream;

public class JavaMailUtils {

	static InputStream is;
	
	/**
	 * 初始化静态变量
	 */
	static {
		is = JavaMailUtils.class.getResourceAsStream("jk_web\\mail\\mail.properties");
	}

	public static void main(String[] args) {
		System.out.println(is);
	}
}
