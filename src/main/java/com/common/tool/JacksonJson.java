package com.common.tool;


import java.io.StringWriter;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

public class JacksonJson {
	private static ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.setDateFormat(DateUtils.defaultDateFormat);
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
	}

	public String getJsonType() {
		return "jackson";
	}

	public static <T> T fromJson(String json, Class<T> cls) {
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		try {
			return mapper.readValue(json, cls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String toJson(Object obj) {
		StringWriter sw = new StringWriter();
		try {
			mapper.writeValue(sw, obj);
			return sw.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
