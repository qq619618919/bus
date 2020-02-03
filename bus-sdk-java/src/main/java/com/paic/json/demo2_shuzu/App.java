package com.paic.json.demo2_shuzu;

import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
	public static void main(String[] args) throws Exception {
		// 实例化 ObjectMapper 对象
		ObjectMapper objectMapper = new ObjectMapper();

		// json 消息
		String json = "[{\"firstname\":\"Bo\",\"lastname\":\"Shang\"}, {\"firstname\":\"San\",\"lastname\":\"Zhang\"}]";

		// 将 json 转成数组
		Person[] people = objectMapper.readValue(json, Person[].class);
		for(Person p: people) {
			System.out.println(p);
		}
	}
}