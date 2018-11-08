 package com.infosys.Employee_final.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.infosys.Employee_final.model.Employee;
import com.infosys.Employee_final.service.EmployeeServiceImpl;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;
	
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	@ResponseBody
	public List<Employee> getEmployeesDetails() throws Exception {
		return employeeServiceImpl.getEmployeesDetails();
	}
	
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Employee getEmployeeDetails(@PathVariable Integer id) throws Exception {
		return employeeServiceImpl.getSpecificEmployeeDetails(id);
	}
	
	@RequestMapping(value = "/employee", method = RequestMethod.POST)
    public ResponseEntity<?> createEmployee(@Valid @RequestBody Employee employee, BindingResult bindingResult) throws Exception {
		String msg = null;
		HttpStatus status = null; 
		try {
			if(bindingResult.hasErrors()) {
				msg = "Invalid Data";
				status = HttpStatus.NOT_ACCEPTABLE;
				throw new Exception(bindingResult.getFieldError().getDefaultMessage());
			}
			Integer id = employeeServiceImpl.saveEmployee(employee);
			msg = "Employee Saved with Id:"+id;
			status = HttpStatus.CREATED;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
        return new ResponseEntity<String>(msg,status);
    }
	
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
	public String deleteEmployee(@PathVariable Integer id) throws Exception {
		return employeeServiceImpl.deleteEmployee(id);
	}
	
	@RequestMapping(value = "/employee", method = RequestMethod.PUT)
	public void updateEmployee(@Valid @RequestBody Employee employee,BindingResult bindingResult) throws Exception {
		try {
			if(bindingResult.hasErrors()) {
				throw new Exception(bindingResult.getFieldError().getDefaultMessage());
			}
			employeeServiceImpl.updateEmployee(employee);	
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
