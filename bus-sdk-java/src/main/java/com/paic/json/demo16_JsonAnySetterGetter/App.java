package com.paic.json.demo16_JsonAnySetterGetter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class App {
	public static void main(String[] args) throws Exception {
		// 实例化 ObjectMapper 对象
		ObjectMapper objectMapper = new ObjectMapper();

		// json 消息
		String json = "{\"firstname\":\"Bo\",\"lastname\":\"Shang\"}";
		String json1 = "[\"aa\",\"bb\"]";

//		Person01 person01 = objectMapper.readValue(json1, Person01.class);
		// 将 json 转成对象
		List<String> shangbo = objectMapper.readValue(json1, new TypeReference<List<String>>(){});
		System.out.println(shangbo);
	}

}