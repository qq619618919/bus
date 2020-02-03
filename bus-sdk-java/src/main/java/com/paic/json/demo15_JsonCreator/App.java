package com.paic.json.demo15_JsonCreator;

import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
	public static void main(String[] args) throws Exception {
		// 实例化 ObjectMapper 对象
		ObjectMapper objectMapper = new ObjectMapper();

		// json 消息
		String json = "{\"firstname\":\"Bo\",\"lastname\":\"Shang\"}";

		// 将 json 转成对象
		Person shangbo = objectMapper.readValue(json, Person.class);
		System.out.println(shangbo);
	}

}