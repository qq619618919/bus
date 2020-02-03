package com.paic.json.demo6_deserializationFeature;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
	public static void main(String[] args) throws Exception {
		// 实例化 ObjectMapper 对象
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 忽略未知字段

		// json 消息
		String json = "{\"firstname\":\"Bo\",\"lastname\":\"Shang\",\"brithday\":\"2019-05-25\"}";

		// 将 json 转成对象，忽略 brithday
		Person shangbo = objectMapper.readValue(json, Person.class);
		System.out.println(shangbo);
	}
}