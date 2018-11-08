package com.infosys.Employee_final.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Address {
	
	@NotEmpty(message = "AddressId must not be blank.")
	private Integer Id;
	
	@NotEmpty(message = "City must not be blank.")
	private String City;
	
	@NotEmpty(message = "Country must not be blank.")
	private String country;
	
	@Size(min = 6, max =6, message = "ZipCode must be of 6")
	@NotEmpty(message = "zipCode must not be blank.")
	private Integer zipcode;
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Integer getZipcode() {
		return zipcode;
	}
	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}
	
	
}
