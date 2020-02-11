package com.madushan.app.service;

import com.madushan.app.model.Employee;

public interface EmployeeService {

	Employee save(Employee e);

	Employee findById(int id);

	String deleteEmployee(int id);
}
