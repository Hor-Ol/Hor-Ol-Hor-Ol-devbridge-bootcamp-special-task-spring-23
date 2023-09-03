package com.dvbg.employeescsvuploader;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dvbg.employeescsvuploader.entity.Employee;
import com.dvbg.employeescsvuploader.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeesCsvUploaderApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	EmployeeRepository employeeRepository;

	@Test
	public void testSaveEmployees() throws Exception {
		List<Employee> employeesList = new ArrayList<>();
		employeesList.add(new Employee("Hodor", "Hodor", "hodor@westeros.com", "Stark", "The North"));
		employeesList.add(new Employee("Hot", "Pie", "hotpie@westeros.com", "The Night's Watch", "The North"));

		ObjectMapper objectMapper = new ObjectMapper();
		String employeesJSON = objectMapper.writeValueAsString(employeesList);

		RequestBuilder request = MockMvcRequestBuilders.post("http://localhost:8080/employee/uploadAll")
				.contentType(MediaType.APPLICATION_JSON)
				.content(employeesJSON);

		mockMvc.perform(request)
				.andExpect(status().isOk());
	}

	@BeforeEach
	void clearAndPopulateEmployees() {
		employeeRepository.deleteAll();

		List<Employee> employeesList = new ArrayList<>();
		employeesList.add(new Employee("Hodor", "Hodor", "hodor@westeros.com", "Stark", "The North"));
		employeesList.add(new Employee("Hot", "Pie", "hotpie@westeros.com", "The Night's Watch", "The North"));

		employeeRepository.saveAll(employeesList);
	}

	@Test
	public void testGetEmployees() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.get("http://localhost:8080/employee/all");

		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(2)))
				.andExpect(jsonPath("$[0].first_name", Matchers.is("Hodor")))
				.andExpect(jsonPath("$[1].first_name", Matchers.is("Hot")));
		;
	}

	@Test
	public void testDeleteEmployees() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.delete("http://localhost:8080/employee/all");

		mockMvc.perform(request)
				.andExpect(status().isNoContent());
	}
}
