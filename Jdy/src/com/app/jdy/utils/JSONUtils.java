package com.app.jdy.utils;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtils {

	/**
	 * @param key
	 *            取值JSONObject的某字段,若空返回defValue
	 * @param args
	 *            默认值
	 * @throws JSONException
	 */
	public static String getString(JSONObject jsonObject, String key, String defValue) throws JSONException {
		if (jsonObject.optString(key) == null || jsonObject.optString(key).equals("null")
				|| jsonObject.optString(key).equals("")) {
			return defValue;
		} else {
			return jsonObject.optString(key);
		}
	}

	/**
	 * 判断key字段是否存在并有值
	 * 
	 * @param jsonObject
	 * @param key
	 * @return
	 * @throws JSONException
	 */
	public static Boolean isEmpty(JSONObject jsonObject, String key) throws JSONException {
		if (jsonObject.optString(key) == null || jsonObject.optString(key).equals("null")
				|| jsonObject.optString(key).equals("")) {
			return true;
		} else {
			return false;
		}
	}

	public static <T> T toBean(String jsonStr, Class<T> valueType) {
		ObjectMapper mapper = new ObjectMapper();
		//忽略json字符串中,实体没有的字段
//		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			return mapper.readValue(jsonStr, valueType);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
