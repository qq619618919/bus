package com.paic.json.demo15_JsonCreator;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
	private String firstname;
	private String lastname;
	
	@JsonCreator
	public Person(
			@JsonProperty("firstname") String firstname, 
			@JsonProperty("lastname") String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}
}