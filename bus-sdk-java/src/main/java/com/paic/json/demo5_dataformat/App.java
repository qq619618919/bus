package com.paic.json.demo5_dataformat;

import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
	public static void main(String[] args) throws Exception {
		// 实例化 ObjectMapper 对象
		ObjectMapper objectMapper = new ObjectMapper();
		
		// 设置日期格式
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		objectMapper.setDateFormat(dateFormat);

		// 将对象转成 json
		String json = objectMapper.writeValueAsString(newPerson());
		System.out.println(json);

		// 将 json 转成对象
		Person shangbo = objectMapper.readValue(json, Person.class);
		System.out.println(shangbo);
	}

	private static Person newPerson() {
		Person shangbo = new Person();
		shangbo.setFirstname("Bo");
		shangbo.setLastname("Shang");
		shangbo.setBrithday(new Date());
		
		return shangbo;
	}
}