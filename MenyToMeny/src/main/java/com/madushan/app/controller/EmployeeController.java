package com.madushan.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.TransactionalException;

import com.madushan.app.model.Address;
import com.madushan.app.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.madushan.app.model.Project;
import com.madushan.app.model.Telephone;
import com.madushan.app.service.EmployeeService;

@RestController
@RequestMapping("service")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;


	@RequestMapping("test")
	public Employee test() {
		Employee employee = new Employee();
		employee.setId(10);
		employee.setName("Madushan");
		employee.setMarks("80");

		Address adress = new Address();
		adress.setCity("kalaniya");
		employee.setAddress(adress);

		Telephone telephone = new Telephone();
		telephone.setNo(0766717617);

		Telephone telephone1 = new Telephone();
		telephone1.setNo(114567890);

		List<Telephone> list = new ArrayList<>();
		list.add(telephone);
		list.add(telephone1);


		employee.setTelephones(list);

		employee.getTelephones().stream().forEach(t -> {
			t.setEmployee(employee);
		});

		Project project = new Project();
		project.setName("project1");

		Project project1 = new Project();
		project1.setName("project2");

		List<Project> projectList = new ArrayList<Project>();
		projectList.add(project);
		projectList.add(project1);
		// Project info ends
		
		employee.setProjects(projectList);

		return employee;
	}


	@RequestMapping("/employee/{id}")
	public Employee getEmployee(@PathVariable int id) {
		if (id == 0) {
			Employee employee = new Employee();
			employee.setId(10);
			employee.setName("ishan");
			return employee;
		} else
			return employeeService.findById(id);

	}


	@RequestMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable int id) {
		return employeeService.deleteEmployee(id);
	}

	@Transactional(rollbackOn = TransactionalException.class)
	@RequestMapping(value = "/employees", method = RequestMethod.POST)
	public Employee getEmployees(@RequestBody Employee e) {

		e.getTelephones().stream().forEach(t -> {
			t.setEmployee(e);
		});

		Employee eee = employeeService.save(e);
		return eee;
	}
}
