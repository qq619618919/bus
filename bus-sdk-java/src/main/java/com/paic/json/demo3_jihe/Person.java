package com.paic.json.demo3_jihe;

import org.apache.commons.lang3.builder.ToStringBuilder;

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