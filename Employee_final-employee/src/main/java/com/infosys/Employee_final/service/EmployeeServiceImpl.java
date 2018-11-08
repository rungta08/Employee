package com.infosys.Employee_final.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import com.infosys.Employee_final.entity.EmployeeEntity;
import com.infosys.Employee_final.exception.InvalidEmployeeIdException;
import com.infosys.Employee_final.model.Address;
import com.infosys.Employee_final.model.Employee;
import com.infosys.Employee_final.repository.EmployeeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
		
	@Autowired
	private Environment environment;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getEmployeesDetails() throws Exception {
		List<Employee> employeeList = new ArrayList<Employee>();
		List<EmployeeEntity> employeeEntityList = new ArrayList<EmployeeEntity>();
		try {
			employeeEntityList = employeeRepository.findAll();
			if(employeeEntityList.size() == 0) {
				throw new InvalidEmployeeIdException(environment.getProperty("EmployeeServiceImpl.NO_DATA_FOUND"));
			}
		}
		catch(InvalidEmployeeIdException e) {
			System.out.println(e.getMessage());
		}
		for(int i = 0;i < employeeEntityList.size();i++) {
			Employee employee = new Employee();
			Address address = new Address();
			employee = employee.entityToModel(employeeEntityList.get(i));
			address = new RestTemplate().getForObject("http://localhost:8200/address/"+employeeEntityList.get(i).getAddressId(), Address.class);
			employee.setAddress(address);
			employeeList.add(employee);
		}
		return employeeList;
	}
	@Override
	public Integer saveEmployee(Employee employee){
		EmployeeEntity employeeEntity = new EmployeeEntity();
		BeanUtils.copyProperties(employee,employeeEntity);
		Integer id = new RestTemplate().postForObject("http://localhost:8200/address", employee.getAddress(), Integer.class);
		employeeEntity.setAddressId(id);
		Integer eid = employeeRepository.saveAndFlush(employeeEntity).getId();
		logger.info("Employee Saved with Id:" +eid);
		return eid;
	}
	
	@Override
	public String deleteEmployee(Integer id) throws Exception{
		String msg = null;
		try {
			if(!employeeRepository.existsById(id)) {
				msg = "Data Not Found";
				throw new InvalidEmployeeIdException(environment.getProperty("EmployeeServiceImpl.INVALID_EMPLOYEE_ID"));
			}
			EmployeeEntity employeeEntity = new EmployeeEntity();
			employeeEntity = employeeRepository.findById(id).get();
			employeeRepository.deleteById(id);
			new RestTemplate().delete("http://localhost:8200/address/"+employeeEntity.getAddressId());
			msg = "Employee Deleted with Id:"+id;
			logger.info("Employee Deleted for Id" +id);
		}
		catch(InvalidEmployeeIdException e) {
			System.out.println(e.getMessage());
		}
		return msg;
	}
	
	@Override
	public void updateEmployee(Employee employee) {
		EmployeeEntity employeeEntity = new EmployeeEntity();
		BeanUtils.copyProperties(employee,employeeEntity);
		employeeEntity.setAddressId(employee.getAddress().getId());
		new RestTemplate().put("http://localhost:8200/address",employee.getAddress());
		employeeRepository.save(employeeEntity);
		logger.info("Employee Updated for Id" +employee.getId());
	}
	
	@Override
	public Employee getSpecificEmployeeDetails(Integer id) throws Exception {
		Employee employee = new Employee();
		try {
			if(!employeeRepository.existsById(id)) {
				throw new InvalidEmployeeIdException(environment.getProperty("EmployeeServiceImpl.INVALID_EMPLOYEE_ID"));
			}
			EmployeeEntity employeeEntity = new EmployeeEntity();
			employeeEntity = employeeRepository.findById(id).get();
			BeanUtils.copyProperties(employeeEntity,employee);
			Address address = new Address();
			address = new RestTemplate().getForObject("http://localhost:8200/address/"+employeeEntity.getAddressId(), Address.class);
			employee.setAddress(address);
		}
		catch(InvalidEmployeeIdException e) {
			System.out.println(e.getMessage());
		}
		return employee;
	}
	
	

}
