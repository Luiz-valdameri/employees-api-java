package com.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.employee.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("SELECT e FROM Employee e WHERE CONCAT(e.name, e.surname, e.email, e.nis) LIKE %?1%")
	public List<Employee> search(String keyword);
	
}