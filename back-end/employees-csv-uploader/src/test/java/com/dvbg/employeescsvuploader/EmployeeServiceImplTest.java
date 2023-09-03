package com.dvbg.employeescsvuploader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.dvbg.employeescsvuploader.entity.Employee;
import com.dvbg.employeescsvuploader.repository.EmployeeRepository;
import com.dvbg.employeescsvuploader.service.EmployeeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {

  @Mock
  private EmployeeRepository employeeRepository;

  @InjectMocks
  private EmployeeServiceImpl employeeService;

  @Test
  public void saveEmployeesTest() {
    // Arrange:
    List<Employee> employeesList = new ArrayList<>();
    employeesList.add(new Employee("Hodor", "Hodor", "hodor@westeros.com", "Stark", "The North"));
    employeesList.add(new Employee("Hot", "Pie", "hotpie@westeros.com", "The Night's Watch", "The North"));

    when(employeeRepository.saveAll(employeesList)).thenReturn(employeesList);

    // Act:
    List<Employee> savedEmployees = employeeService.saveEmployees(employeesList);

    // Assert:
    assertEquals("Hodor", savedEmployees.get(0).getFirst_name());
    assertEquals("The North", savedEmployees.get(1).getArea());
  }

  @Test
  public void getEmployees() {
    // Arrange:
    List<Employee> employeesList = new ArrayList<>();
    employeesList.add(new Employee("Hodor", "Hodor", "hodor@westeros.com", "Stark", "The North"));
    employeesList.add(new Employee("Hot", "Pie", "hotpie@westeros.com", "The Night's Watch", "The North"));

    when(employeeRepository.findAll()).thenReturn(employeesList);

    // Act:
    List<Employee> fetchedEmployees = employeeService.getEmployees();

    // Assert:
    assertEquals(employeesList, fetchedEmployees);
  }

  @Test
  public void deleteEmployees() {
    // Arrange:
    doNothing().when(employeeRepository).deleteAll();

    // Act:
    employeeService.deleteEmployees();

    // Assert:
    verify(employeeRepository, times(1)).deleteAll();
  }

}
