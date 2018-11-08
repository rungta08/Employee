package com.infosys.Employee_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.infosys.Employee_final.entity.EmployeeEntity;


public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

}
