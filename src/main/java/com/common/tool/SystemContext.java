package com.common.tool;

import java.io.Serializable;
import java.util.Properties;

public class SystemContext implements Serializable  {
	
	private static Properties properties;
	
	public static String getProperty(String property) {
		if (null != properties)
			return properties.getProperty(property);
		return "";
	}

	public static void setProperties(Properties properties) {
		SystemContext.properties = properties;
	}

}
