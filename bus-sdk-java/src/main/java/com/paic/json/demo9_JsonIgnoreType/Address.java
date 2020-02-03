package com.paic.json.demo9_JsonIgnoreType;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

@JsonIgnoreType
public class Address {
	private String state;
	private String city;
	private String streat;

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreat() {
		return streat;
	}

	public void setStreat(String streat) {
		this.streat = streat;
	}

}