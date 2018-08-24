package com.common.filter;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.common.tool.BeanFactory;
import com.common.tool.SystemContext;

public class AppFilter implements Filter {

	private static Log log = LogFactory.getLog(AppFilter.class);
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		log.info("执行AppFilter");
		arg2.doFilter(arg0, arg1);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		log.info("初始化BeanFactory");
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());//获取spring的context
		BeanFactory.setApplicationContext(context);
		Properties properties=(Properties) BeanFactory.getBean("propertiesFactoryBean");
		SystemContext.setProperties(properties);
	}

}
