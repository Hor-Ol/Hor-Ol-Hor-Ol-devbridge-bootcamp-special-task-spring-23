package com.dvbg.employeescsvuploader.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dvbg.employeescsvuploader.entity.Employee;
import com.dvbg.employeescsvuploader.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  EmployeeRepository employeeRepository;

  @Override
  public List<Employee> saveEmployees(List<Employee> employees) {
    deleteEmployees();
    return (List<Employee>) employeeRepository.saveAll(employees);
  }

  @Override
  public List<Employee> getEmployees() {
    return (List<Employee>) employeeRepository.findAll();
  }

  @Override
  public void deleteEmployees() {
    employeeRepository.deleteAll();
  }

}
