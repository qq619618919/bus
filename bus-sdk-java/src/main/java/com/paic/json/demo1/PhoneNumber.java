package com.paic.json.demo1;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class PhoneNumber {
	private String code;
	private String number;

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
