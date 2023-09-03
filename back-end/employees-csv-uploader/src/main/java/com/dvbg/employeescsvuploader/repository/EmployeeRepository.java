package com.dvbg.employeescsvuploader.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dvbg.employeescsvuploader.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
