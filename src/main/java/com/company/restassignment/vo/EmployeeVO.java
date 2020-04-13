package com.company.restassignment.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
public class EmployeeVO implements Serializable {

	private Integer id;

	@NotNull
	@Pattern(regexp = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$")
	@Size(min = 1, max = 255)
	private String email;

	@NotNull
	@Size(min = 1, max = 255)
	private String name;

	private Integer managerId;

	private Set<Integer> subordinateIds = new HashSet<Integer>();

	private AddressVO address;

	private List<String> roles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public Set<Integer> getSubordinateIds() {
		return subordinateIds;
	}

	public void setSubordinateIds(Set<Integer> subordinateIds) {
		this.subordinateIds = subordinateIds;
	}

	public AddressVO getAddress() {
		return address;
	}

	public void setAddress(AddressVO address) {
		this.address = address;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}