package com.paic.json.demo19_JsonNode;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class App {
	public static void main(String[] args) throws Exception {
		// 实例化 ObjectMapper 对象
		ObjectMapper objectMapper = new ObjectMapper();

		// json 消息
		String json = "{\"firstname\":\"Bo\",\"lastname\":\"Shang\",\"age\":30}";
		
		// 将 json 转成 JsonNode 对象
		JsonNode rootNode = objectMapper.readTree(json);
		
		// 得到节点值
		JsonNode firstNameNode = rootNode.get("firstname");
		System.out.println("firstname:" + firstNameNode.asText());
		
		JsonNode ageNode = rootNode.get("age");
		System.out.println("age:" + ageNode.asInt());
		
		// 创建新节点
		ObjectNode newNode = objectMapper.createObjectNode();
		newNode.setAll((ObjectNode)rootNode);
		newNode.put("hometown", "dalian");
		
		// 将 JsonNode 对象转成 json
		String newjson = objectMapper.writeValueAsString(newNode);
		System.out.println(newjson);
	}
}