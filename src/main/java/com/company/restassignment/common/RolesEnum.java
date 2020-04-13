package com.company.restassignment.common;

public enum RolesEnum {

		BU_HEAD("BU HEAD", 1), LEAD("LEAD", 2), MANAGER("MANAGER", 3),
		DEVELOPER("DEVELOPER", 4), ARCHITECT("ARCHITECT", 5);

	private final String roleName;

	private final Integer roleId;

	private RolesEnum(final String roleName, final Integer roleId) {
		this.roleName = roleName;
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public Integer getRoleId() {
		return roleId;
	}
}
