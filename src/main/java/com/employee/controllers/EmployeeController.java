package com.employee.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.config.InvalidException;
import com.employee.config.LengthException;
import com.employee.controllers.dto.EmployeeDto;
import com.employee.models.Employee;
import com.employee.repository.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping
	public ResponseEntity<List<EmployeeDto>> list() {

		List<Employee> employees = employeeRepository.findAll();

		return ResponseEntity.ok(EmployeeDto.convert(employees));
	}

	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<EmployeeDto>> list(@PathVariable String keyword) {

		List<Employee> employees = employeeRepository.search(keyword);

		return ResponseEntity.ok(EmployeeDto.convert(employees));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Employee>> list(@PathVariable Long id) {
		Optional<Employee> employee = employeeRepository.findById(id);

		return ResponseEntity.ok(employee);
	}

	@PostMapping
	public ResponseEntity<Employee> create(@RequestBody Employee employee) throws LengthException, InvalidException {
		
		if(employee.getName() == null || employee.getNis() == null || employee.getEmail() == null || employee.getNis() == null) {
			throw new InvalidException("Todos os campos devem ser preenchidos!");
		}
		
		if (employee.getName().length() > 30 || employee.getName().length() < 2) {
			throw new LengthException("O nome precisa ter no minimo 2 e no máximo 30 caracteres.");
		}
		if (employee.getSurname().length() > 50 || employee.getSurname().length() < 2) {
			throw new LengthException("O sobrenome precisa ter no minimo 2 e no máximo 50 caracteres.");
		}

		String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(employee.getEmail());
		if (!matcher.matches()) {
			throw new LengthException("O email deve ser válido!");
		}

		return ResponseEntity.ok(employeeRepository.save(employee));
	}

	@PutMapping
	public ResponseEntity<Employee> update(@RequestBody Employee employee) throws InvalidException, LengthException {
		if(employee.getName() == null || employee.getNis() == null || employee.getEmail() == null || employee.getNis() == null) {
			throw new InvalidException("Todos os campos devem ser preenchidos!");
		}
		
		if (employee.getName().length() > 30 || employee.getName().length() < 2) {
			throw new LengthException("O nome precisa ter no minimo 2 e no máximo 30 caracteres.");
		}
		if (employee.getSurname().length() > 50 || employee.getSurname().length() < 2) {
			throw new LengthException("O sobrenome precisa ter no minimo 2 e no máximo 50 caracteres.");
		}

		String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(employee.getEmail());
		if (!matcher.matches()) {
			throw new LengthException("O email deve ser válido!");
		}
		
		return ResponseEntity.ok(employeeRepository.save(employee));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		employeeRepository.deleteById(id);

		return ResponseEntity.ok(id);
	}
}
