package com.paic.json.demo10_JsonInclude;

import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
	public static void main(String[] args) throws Exception {
		// 实例化 ObjectMapper 对象
		ObjectMapper objectMapper = new ObjectMapper();

		// 将对象转成 json
		String json = objectMapper.writeValueAsString(newPerson());
		System.out.println(json);
	}

	private static Person newPerson() {
		Person shangbo = new Person();
		shangbo.setFirstname("Bo");
//		shangbo.setLastname("Shang");

		return shangbo;
	}
}