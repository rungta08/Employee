 package com.infosys.Employee_final.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.infosys.Employee_final.model.Address;
import com.infosys.Employee_final.service.AddressServiceImpl;

@RestController
public class AddressController {
	
	@Autowired
	private AddressServiceImpl addressServiceImpl;
	
	@RequestMapping(value = "/address", method = RequestMethod.GET)
	@ResponseBody
	public List<Address> getAddressDetails() throws Exception {
		return addressServiceImpl.getAddressDetails();
	}
	
	@RequestMapping(value = "/address/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Address getAddressDetails(@PathVariable Integer id) throws Exception {
		return addressServiceImpl.getAddressDetails(id);
	}
	
	@RequestMapping(value = "/address", method = RequestMethod.POST)
    public Integer createAddress(@Valid @RequestBody Address address, BindingResult bindingResult) throws Exception {
		Integer id = null;
		try {
			if(bindingResult.hasErrors()) {
				System.out.println(bindingResult.getFieldValue("zipcode"));
				throw new Exception(bindingResult.getFieldError().getDefaultMessage());
			}
			id = addressServiceImpl.saveAddress(address);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
        return id;
    }
	
	@RequestMapping(value = "/address/{id}", method = RequestMethod.DELETE)
	public String deleteAddress(@PathVariable Integer id) throws Exception {
		return addressServiceImpl.deleteAddress(id);
	}
	
	@RequestMapping(value = "/address", method = RequestMethod.PUT)
	public Boolean updateAddress(@Valid @RequestBody Address address,BindingResult bindingResult) throws Exception {
		Boolean status = null;
		try {
			if(bindingResult.hasErrors()) {
				status = false;
				throw new Exception(bindingResult.getFieldError().getDefaultMessage());
			}
			addressServiceImpl.updateAddress(address);
			status = true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return status;
	}
}
