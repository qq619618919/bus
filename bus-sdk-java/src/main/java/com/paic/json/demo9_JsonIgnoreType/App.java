package com.paic.json.demo9_JsonIgnoreType;

import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
	public static void main(String[] args) throws Exception {
		// 实例化 ObjectMapper 对象
		ObjectMapper objectMapper = new ObjectMapper();

		// 将对象转成 json
		String json = objectMapper.writeValueAsString(newPerson());
		System.out.println(json);
		json = "{\"firstname\":\"Bo\",\"lastname\":\"Shang\",\"address\":{\"state\":\"LiaoNing\",\"city\":\"DaLian\",\"streat\":\"GaoXingQu\"}}";
		// 将 json 转成对象
		Person shangbo = objectMapper.readValue(json, Person.class);
		System.out.println(shangbo);
	}

	private static Person newPerson() {
		Address address = new Address();
		address.setState("LiaoNing");
		address.setCity("DaLian");
		address.setStreat("GaoXingQu");

		Person shangbo = new Person();
		shangbo.setFirstname("Bo");
		shangbo.setLastname("Shang");
		shangbo.setAddress(address);

		return shangbo;
	}
}