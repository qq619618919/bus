package com.paic.json.demo1;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class App {
	@Test
	public void convert() throws Exception {
		// 实例化 ObjectMapper 对象
		ObjectMapper objectMapper = new ObjectMapper();

		// 将对象转成 json
		String json = objectMapper.writeValueAsString(newPerson());
		System.out.println(json);

		// 将 json 转成对象
		Person shangbo = objectMapper.readValue(json, Person.class);
		System.out.println(shangbo);
	}

	private static Person newPerson() {
		List<PhoneNumber> phones = new ArrayList<>();

		PhoneNumber phone = new PhoneNumber();
		phone.setCode("86");
		phone.setNumber("0411-12345678");
		phones.add(phone);

		PhoneNumber cell = new PhoneNumber();
		cell.setCode("86");
		cell.setNumber("18912345678");
		phones.add(cell);

		Address address = new Address();
		address.setState("LiaoNing");
		address.setCity("DaLian");
		address.setStreat("GaoXingQu");

		Person shangbo = new Person();
		shangbo.setFirstname("Bo");
		shangbo.setLastname("Shang");
		shangbo.setAddress(address);
		shangbo.setPhones(phones);

		return shangbo;
	}
}