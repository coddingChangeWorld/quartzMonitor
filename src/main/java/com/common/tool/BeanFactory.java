package com.common.tool;

import org.springframework.context.ApplicationContext;

public class BeanFactory {
	
	//把WebApplicationContext放到静态对象里面
		private static ApplicationContext context = null;
		public static void setApplicationContext(ApplicationContext context){
			BeanFactory.context = context;
		}

		public static Object getBean(String beanName){
			//加一个扩展获取bean的方式
			if(context.containsBean(beanName)){
				return context.getBean(beanName);
			}
			return null;
		}
		/**
		 * 获取spring bean实例
		 * @Methodname: getBean
		 * @Discription: 如果beanName得不到实例，则用defaultBeanName来得
		 * @param beanName
		 * @param defaultBeanName
		 * @return
		 *
		 */
		public static Object getBean(String beanName, String defaultBeanName){
			//加一个扩展获取bean的方式
			Object obj = getBean(beanName);
			if(obj==null){
				obj = getBean(defaultBeanName);
			}
			return obj;
		} 


}
