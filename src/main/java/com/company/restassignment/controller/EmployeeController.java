package com.company.restassignment.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.restassignment.service.EmployeeService;
import com.company.restassignment.vo.EmployeeVO;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping(value = "/employees/{id}")
	public ResponseEntity<EmployeeVO> fetchEmployee(@PathVariable Integer id) {
		ResponseEntity<EmployeeVO> responseEntity = null;
		final EmployeeVO employee = employeeService.fetchEmployee(id);
		if (employee != null) {
			responseEntity = ResponseEntity.ok(employee);
		} else {
			responseEntity = ResponseEntity.notFound().build();
			// responseEntity = new ResponseEntity<EmployeeVO>(
			// HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	@PostMapping(value = "/employees")
	public ResponseEntity<Void> saveEmployee(
		@Valid @RequestBody EmployeeVO emp) {
		ResponseEntity<Void> responseEntity = null;
		final EmployeeVO employee = employeeService
			.fetchEmployeeByEmail(emp.getEmail());
		if (employee != null) {
			responseEntity = new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			employeeService.saveEmployee(emp);
			responseEntity = new ResponseEntity<Void>(HttpStatus.CREATED);
		}

		// TODO: HATEOS, Swagger, Exception Handling, formatting, commenting
		return responseEntity;
	}
}
