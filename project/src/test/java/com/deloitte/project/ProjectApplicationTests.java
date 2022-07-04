package com.deloitte.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
@SpringBootTest
class ProjectApplicationTests {

	@Test
	void contextLoads() {
	}


//	@Autowired
//	private WebApplicationContext webApplicationContext;
//
//	private MockMvc mockMvc;
//
//	@BeforeClass
//	public void setup() {
//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//	}
//
//	@Test
//	public void testEmployee() throws Exception {
//		mockMvc.perform(get("/saveTodoList")).andExpect(status().isOk())
//				.andExpect(content().contentType("application/json;charset=UTF-8"))
//				.andExpect(jsonPath("$.name").value("emp1")).andExpect(jsonPath("$.designation").value("manager"))
//				.andExpect(jsonPath("$.empId").value("1")).andExpect(jsonPath("$.salary").value(3000));
//
//	}

}
