package com.infosys.Employee_final.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.infosys.Employee_final.entity.EmployeeEntity;

public class Employee {
	
	private Integer Id;
	
	@NotEmpty(message = "First Name must not be blank.")
    @Size(min = 4, message = "First Name must be minimum of 4.")
	private String firstname;
	
	@NotEmpty(message = "Last Name must not be blank.")
    @Size(min = 4, message = "Last Name must be minimum of 4.")
	private String lastname;
	
	@Pattern(regexp = "male|female", flags = Pattern.Flag.CASE_INSENSITIVE)
	private String gender;
	
	private Long phonenumbers;
	
	@NotEmpty(message = "Email must not be blank.")
    @Email
	private String email;
	
	private Address address;
	
	private Long Salary;
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Long getPhonenumbers() {
		return phonenumbers;
	}
	public void setPhonenumbers(Long phonenumbers) {
		this.phonenumbers = phonenumbers;
	}
	public Long getSalary() {
		return Salary;
	}
	public void setSalary(Long salary) {
		Salary = salary;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Employee entityToModel(EmployeeEntity employeeEntity) {
		Employee employee = new Employee();
		employee.setId(employeeEntity.getId());
		employee.setFirstname(employeeEntity.getFirstname());
		employee.setLastname(employeeEntity.getLastname());
		employee.setEmail(employeeEntity.getEmail());
		employee.setGender(employeeEntity.getGender());
		employee.setPhonenumbers(employeeEntity.getPhonenumbers());
		employee.setSalary(employeeEntity.getSalary());
		return employee;
	}
	
}
