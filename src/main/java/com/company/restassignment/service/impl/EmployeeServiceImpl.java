package com.company.restassignment.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.restassignment.common.RolesEnum;
import com.company.restassignment.model.Address;
import com.company.restassignment.model.Employee;
import com.company.restassignment.model.Role;
import com.company.restassignment.repository.EmployeeRepository;
import com.company.restassignment.service.EmployeeService;
import com.company.restassignment.vo.AddressVO;
import com.company.restassignment.vo.EmployeeVO;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public EmployeeVO fetchEmployee(final Integer empId) {
		EmployeeVO build = null;
		Optional<Employee> findById = employeeRepository.findById(empId);
		if (findById.isPresent()) {
			final Employee employee = findById.get();
			build = build(employee);
		}
		return build;
	}

	@Override
	public EmployeeVO fetchEmployeeByEmail(final String email) {
		final Employee employee = employeeRepository.findByEmail(email);
		return build(employee);
	}

	private EmployeeVO build(final Employee employee) {
		if (employee != null) {
			final EmployeeVO employeeVO = new EmployeeVO();
			employeeVO.setId(employee.getId());
			employeeVO.setEmail(employee.getEmail());
			employeeVO.setName(employee.getName());

			Employee manager = employee.getManager();
			employeeVO.setManagerId(manager != null ? manager.getId() : null);

			final List<Role> roles = employee.getRoles();
			final List<String> rolesVO = roles.stream()
				.map(role -> role.getName()).collect(Collectors.toList());
			employeeVO.setRoles(rolesVO);

			final Set<Employee> subordinates = employee.getSubordinates();
			final Set<Integer> subordinateIds = subordinates.stream()
				.map(emp -> emp.getId()).collect(Collectors.toSet());
			employeeVO.setSubordinateIds(subordinateIds);

			final Address address = employee.getAddress();
			final AddressVO addressVO = new AddressVO();
			addressVO.setStreet(address.getStreet());
			addressVO.setCity(address.getCity());
			addressVO.setState(address.getState());
			employeeVO.setAddress(addressVO);
			return employeeVO;
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public void saveEmployee(final EmployeeVO emp) {
		final Employee build = build(emp);
		employeeRepository.save(build);
	}

	private Employee build(final EmployeeVO employeeVO) {
		final Employee employee = new Employee();
		employee.setEmail(employeeVO.getEmail());
		employee.setName(employeeVO.getName());
		employee.setManager(
			employeeRepository.findById(employeeVO.getManagerId()).get());

		final List<String> roles = employeeVO.getRoles();
		final List<Role> rolesVO = roles.stream()
			.map(role -> new Role(RolesEnum.valueOf(role).getRoleId(),
				RolesEnum.valueOf(role).getRoleName()))
			.collect(Collectors.toList());
		employee.setRoles(rolesVO);

		final AddressVO addressVO = employeeVO.getAddress();
		final Address address = new Address();
		address.setStreet(addressVO.getStreet());
		address.setCity(addressVO.getCity());
		address.setState(addressVO.getState());
		employee.setAddress(address);

		return employee;
	}

}
