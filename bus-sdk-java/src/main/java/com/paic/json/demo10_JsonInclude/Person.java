package com.paic.json.demo10_JsonInclude;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY) // 忽略空字段，默认空字段也会输出
public class Person {
	private String firstname;
	private String lastname;

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}