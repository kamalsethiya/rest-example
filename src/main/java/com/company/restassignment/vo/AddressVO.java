package com.company.restassignment.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddressVO {

	@NotNull
	@Size(min = 1, max = 255)
	private String street;

	@NotNull
	@Size(min = 1, max = 255)
	private String city;

	@NotNull
	@Size(min = 1, max = 255)
	private String state;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}