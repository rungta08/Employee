package com.infosys.Employee_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.infosys.Employee_final.entity.AddressEntity;


public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {

}
