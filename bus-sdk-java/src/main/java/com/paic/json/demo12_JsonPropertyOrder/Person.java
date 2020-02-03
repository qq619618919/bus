package com.paic.json.demo12_JsonPropertyOrder;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"firstname", "lastname", "birthday"})
public class Person {
	private String firstname;
	private Date birthday;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}