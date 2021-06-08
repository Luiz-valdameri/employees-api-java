package com.employee.controllers.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.employee.models.Employee;

public class EmployeeDto {

	public EmployeeDto(Employee employee) {
		this.id = employee.getId();
		this.name = employee.getName();
		this.surname = employee.getSurname();
		this.email = employee.getEmail();
		this.nis = employee.getNis();

	}

	private Long id;
	private String name;
	private String surname;
	private String email;
	private Long nis;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getNis() {
		return nis;
	}

	public void setNis(Long nis) {
		this.nis = nis;
	}
	
	public static List<EmployeeDto> convert(List<Employee> employees){
		return employees.stream().map(EmployeeDto::new).collect(Collectors.toList());
	}

}
