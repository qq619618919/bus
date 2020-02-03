package com.paic.json.demo20_JsonParser;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class App {
	public static void main(String[] args) throws Exception {
		// json 消息
		String json = "{\"firstname\":\"Bo\",\"lastname\":\"Shang\",\"age\":30}";

		// 实例化 JsonFactory 和 JsonParser 对象
		JsonFactory factory = new JsonFactory();
		JsonParser parser = factory.createParser(json);
		
		// 解析 json
		while(!parser.isClosed()) {
			JsonToken jsonToken = parser.nextToken();
			
			if(JsonToken.FIELD_NAME.equals(jsonToken)) {
				String fieldName = parser.getCurrentName();
				jsonToken = parser.nextToken();
				System.out.println(jsonToken);
				System.out.println(fieldName + ":" + parser.getValueAsString());
			}
		}
	}
}