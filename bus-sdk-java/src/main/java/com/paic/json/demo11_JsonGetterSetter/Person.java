package com.paic.json.demo11_JsonGetterSetter;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Person {
	private String firstname;
	private String lastname;
	private Date birthday;

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

	@JsonGetter("age")
	public Date getBirthday() {
		return birthday;
	}

	@JsonSetter("age")
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}