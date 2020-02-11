package com.madushan.app.service;

import com.madushan.app.model.Employee;
import com.madushan.app.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
    EmployeeRepository employeeRepository;

	@Override
	public Employee save(Employee e) {
		Employee employee = employeeRepository.save(e);
		return employee;
	}

	@Override
	public Employee findById(int id) {
		// TODO Auto-generated method stub
		return employeeRepository.findById(id).get();
	}

	
	@Override
	public String deleteEmployee(int id) {
		// TODO Auto-generated method stub
		 employeeRepository.deleteById(id);
		 return (id!=0)?"Employee deleted with id:-"+id:"NO users";
	}

}
