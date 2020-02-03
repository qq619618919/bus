package com.paic.json.demo1;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Person {
    private String firstname;
    private String lastname;
    private Address address;
    private List<PhoneNumber> phones;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<PhoneNumber> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneNumber> phones) {
        this.phones = phones;
    }

}