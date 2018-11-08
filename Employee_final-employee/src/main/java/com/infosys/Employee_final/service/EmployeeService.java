package com.infosys.Employee_final.service;

import java.util.List;
import com.infosys.Employee_final.model.Employee;

public interface EmployeeService {
	
	public List<Employee> getEmployeesDetails() throws Exception;
	public Employee getSpecificEmployeeDetails(Integer id) throws Exception;
	public Integer saveEmployee(Employee employee);
	public String deleteEmployee(Integer id) throws Exception;
	public void updateEmployee(Employee employee);
}