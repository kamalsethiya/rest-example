package com.company.restassignment.service;

import com.company.restassignment.vo.EmployeeVO;

public interface EmployeeService {

	EmployeeVO fetchEmployee(Integer empId);

	void saveEmployee(EmployeeVO emp);

	EmployeeVO fetchEmployeeByEmail(String email);
}
