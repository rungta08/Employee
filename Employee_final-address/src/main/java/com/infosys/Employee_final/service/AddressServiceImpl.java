package com.infosys.Employee_final.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.infosys.Employee_final.entity.AddressEntity;
import com.infosys.Employee_final.exception.InvalidAddressIdException;
import com.infosys.Employee_final.exception.NoDataFound;
import com.infosys.Employee_final.model.Address;
import com.infosys.Employee_final.repository.AddressRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private AddressRepository addressRepository;

	@SuppressWarnings("unchecked")
	@Override
	public List<Address> getAddressDetails() throws Exception {
		List<AddressEntity> addressEntityList = new ArrayList<AddressEntity>();
		try {
			addressEntityList = addressRepository.findAll();
			if(addressEntityList.size() == 0) {
				throw new NoDataFound(environment.getProperty("AddressServiceImpl.NO_DATA_FOUND"));
			}
		}
		catch(NoDataFound e) {
			System.out.println(e.getMessage());
		}
		return (List<Address>)(Object)addressEntityList;
	}
	
	public Integer saveAddress(Address address){
		AddressEntity addressEntity = new AddressEntity();
		BeanUtils.copyProperties(address,addressEntity);
		Integer id = addressRepository.saveAndFlush(addressEntity).getId();
		logger.info("Address Saved with Id:" +id);
		return id;
	}
	
	public String deleteAddress(Integer id) throws Exception{
		String msg = null; 
		try{
			if(!addressRepository.existsById(id)) {
				msg = "Data Not Found";
				throw new InvalidAddressIdException(environment.getProperty("AddressServiceImpl.INVALID_ADDRESS_ID"));
			}
			addressRepository.deleteById(id);
			msg = "Address Deleted for Id"+id;
			logger.info("Address Deleted for Id" +id);
		}
		catch(InvalidAddressIdException e) {
			System.out.println(e.getMessage());
		}
		return msg;
	}
	
	public void updateAddress(Address address) {
		AddressEntity addressEntity = new AddressEntity();
		BeanUtils.copyProperties(address,addressEntity);
		addressRepository.save(addressEntity);
		logger.info("Address Updated for Id" +address.getId());
	}

	@Override
	public Address getAddressDetails(Integer id) throws Exception {
		Address address = new Address();
		try {
			if(!addressRepository.existsById(id)) {
				throw new InvalidAddressIdException(environment.getProperty("AddressServiceImpl.INVALID_ADDRESS_ID"));
			}
			AddressEntity addressEntity = new AddressEntity();
			addressEntity = addressRepository.findById(id).get();
			BeanUtils.copyProperties(addressEntity,address);
		}
		catch(InvalidAddressIdException e) {
			System.out.println(e.getMessage());
		}
		return address;
	}

}
