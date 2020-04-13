package com.company.restassignment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.company.restassignment.controller.EmployeeController;
import com.company.restassignment.service.EmployeeService;
import com.company.restassignment.vo.EmployeeVO;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	/*
	 * We use @MockBean because the WebApplicationContext does not provide
	 * any @Component, @Service or @Repository beans instance/bean of this
	 * service in its context. It only loads the beans solely required for
	 * testing the controller.
	 */
	@MockBean
	private EmployeeService employeeService;

	@Test
	public void testFetchEmployee() throws Exception {
		EmployeeVO emp = new EmployeeVO();
		emp.setId(1);
		emp.setEmail("kamal.sethiya@gmail.com");
		emp.setName("Kamal");
		Mockito.when(employeeService.fetchEmployee(1)).thenReturn(emp);
		MvcResult result = mockMvc
			.perform(MockMvcRequestBuilders.get("/employees/1"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content()
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Kamal"))
			.andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

	@Test
	public void testSaveEmployee() throws Exception {
		EmployeeVO emp = new EmployeeVO();
		emp.setId(1);
		emp.setEmail("kamal.sethiya@gmail.com");
		emp.setName("Kamal");
		Mockito.doNothing().when(employeeService).saveEmployee(emp);
		mockMvc.perform(MockMvcRequestBuilders.post("/employees").content(
			"{\"id\":1,\"email\":\"kamal.sethiya@gmail.com\",\"name\":\"Kamal\"}")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isCreated());
	}
}
