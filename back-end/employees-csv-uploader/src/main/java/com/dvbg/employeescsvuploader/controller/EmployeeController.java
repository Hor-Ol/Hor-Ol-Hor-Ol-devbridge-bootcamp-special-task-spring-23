package com.dvbg.employeescsvuploader.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dvbg.employeescsvuploader.entity.Employee;
import com.dvbg.employeescsvuploader.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/employee")
@Tag(name = "Employees List Controller", description = "Save, get and delete emploees list data")
public class EmployeeController {

  EmployeeService employeeService;

  @PostMapping(value = "/uploadAll", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Uploads, saves and hands over an array of employees", description = "Operation recieves an array of employees, saves it in the database and returns back saved employees array")
  @ApiResponse(responseCode = "200", description = "Successful uploading, saving and returning of the employees data from the database", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Employee.class))))
  public ResponseEntity<List<Employee>> saveEmployees(@RequestBody List<Employee> employees) {
    return new ResponseEntity<>(employeeService.saveEmployees(employees), HttpStatus.OK);
  }

  @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Gets and returns an array of saved employees", description = "Operation retrieves an array of saved employees from the database and returns it to the user")
  @ApiResponse(responseCode = "200", description = "Successful returning of the employees data from the database", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Employee.class))))
  public ResponseEntity<List<Employee>> getEmployees() {
    return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.OK);
  }

  @DeleteMapping(value = "/all")
  @Operation(summary = "Deletes employees data saved in the database", description = "Operation deletes the employees data stored in the database")
  @ApiResponse(responseCode = "204", description = "Successful deletion of the employees data from the database")
  public ResponseEntity<HttpStatus> deleteEmployees() {
    employeeService.deleteEmployees();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
