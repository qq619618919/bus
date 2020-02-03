package com.paic.json.demo21_JsonGenerator;

import java.io.File;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

public class App {
	public static void main(String[] args) throws Exception {
		// 实例化 JsonFactory 和 JsonParser 对象
		JsonFactory factory = new JsonFactory();
		JsonGenerator gernerator = factory.createGenerator(new File("D:\\people.json"), JsonEncoding.UTF8);
		
		// 生成 json
		gernerator.writeStartObject();
		gernerator.writeStringField("firstname", "Bo");
		gernerator.writeStringField("lasttname", "Shang");
		gernerator.writeNumberField("age", 30);
		gernerator.writeEndObject();
		gernerator.close();
	}
}