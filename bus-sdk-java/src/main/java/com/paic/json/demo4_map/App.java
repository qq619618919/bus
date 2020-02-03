package com.paic.json.demo4_map;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
	public static void main(String[] args) throws Exception {
		// 实例化 ObjectMapper 对象
		ObjectMapper objectMapper = new ObjectMapper();

		// json 消息
		String json = "{\"firstname\":\"Bo\",\"lastname\":\"Shang\"}";

		// 将 json 转成映射
		Map<String, Object> map = objectMapper.readValue(json, new TypeReference<Map<String, Object>>(){});
		System.out.println(map);
	}
}