package com.paic.json.demo16_JsonAnySetterGetter;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class Person {
	private Map<String, Object> properties = new HashMap<>();

	@JsonAnySetter
	public void set(String fieldName, Object val) {
		this.properties.put(fieldName, val);
	}

	@JsonAnyGetter
	public Object get(String fieldName) {
		return this.properties.get(fieldName);
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}