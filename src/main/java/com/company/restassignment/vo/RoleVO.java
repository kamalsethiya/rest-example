package com.company.restassignment.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
public class RoleVO implements Serializable {

	private Integer id;

	@NotNull
	@Size(min = 1, max = 255)
	private String name;

	public RoleVO() {

	}

	public RoleVO(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
