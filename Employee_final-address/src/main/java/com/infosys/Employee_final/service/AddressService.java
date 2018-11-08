package com.infosys.Employee_final.service;

import java.util.List;
import com.infosys.Employee_final.model.Address;

public interface AddressService {
	
	public List<Address> getAddressDetails() throws Exception;
	public Address getAddressDetails(Integer id) throws Exception;
	public Integer saveAddress(Address address);
	public String deleteAddress(Integer id) throws Exception;
	public void updateAddress(Address address);
}