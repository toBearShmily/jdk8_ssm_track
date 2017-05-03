package com.shmily.filter;

import ch.qos.logback.ext.spring.web.WebLogbackConfigurer;
import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

public class LogbackConfigListener implements ServletContextListener {

	private Logger log = LoggerFactory.getLogger(LogbackConfigListener.class);
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		String path = event.getServletContext().getRealPath(SystemUtils.FILE_SEPARATOR+"WEB-INF"+SystemUtils.FILE_SEPARATOR);

		File file = new File(path,"logs");
		if (!file.exists()) {
			file.mkdir();
		}
		System.setProperty("webapp.shmily", path+SystemUtils.FILE_SEPARATOR+"logs");
		log.info("加载日志成功！");
		WebLogbackConfigurer.initLogging(event.getServletContext());
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		WebLogbackConfigurer.shutdownLogging(arg0.getServletContext());
	}
}
