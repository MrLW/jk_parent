package com.lw.jk.action.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("初始化啦啦啦啦啦啦啦啦绿绿绿绿绿绿绿绿绿绿绿绿");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("销毁啦啦啦啦啦啦啦啦绿绿绿绿绿绿绿绿绿绿绿绿");
		
	}

}
