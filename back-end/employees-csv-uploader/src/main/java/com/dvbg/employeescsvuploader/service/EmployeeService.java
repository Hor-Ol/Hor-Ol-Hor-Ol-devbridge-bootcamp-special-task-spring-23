package com.dvbg.employeescsvuploader.service;

import java.util.List;

import com.dvbg.employeescsvuploader.entity.Employee;

public interface EmployeeService {

  List<Employee> getEmployees();

  List<Employee> saveEmployees(List<Employee> employees);

  void deleteEmployees();

}
