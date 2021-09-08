package com.bitbuy.usermanagement.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	public static String convertJsonToString(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString =  "";
		if(obj != null) {
			jsonString = mapper.writeValueAsString(obj);
		}
		return jsonString;
	}
}
