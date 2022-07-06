package com.deloitte.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.deloitte.project.model.Authenticationrequest;
import com.deloitte.project.model.Todolist;
import org.json.JSONObject;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;


/**
 *
 *
 * @author Rahul Ramesh
 *
 */


@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ProjectApplicationTests {

	@Test
	void contextLoads() {
	}

	public String jwtToken;
	@Autowired
	TestRestTemplate restTemplate;

	@Test
	@Order(1)
	public void testRegister() throws Exception {
		Authenticationrequest userDetails = new Authenticationrequest("user","pass123");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Authenticationrequest> entity = new HttpEntity<>(userDetails, headers);
		ResponseEntity<String> result = restTemplate.postForEntity("/register",entity, String.class);
		System.out.println(result.getStatusCode());
		assertTrue(result.getStatusCode() == HttpStatus.OK);
	}
	@Test
	@Order(2)
	public void testAutenticate() throws Exception{
		jwtToken=getJwt();
		assertTrue(jwtToken!=null);

	}


	@Test
	@Order(3)
	public void updateToDoListTest() throws Exception {
		List<Todolist> toDoTaskList = new ArrayList<>();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
		toDoTaskList.add(new Todolist(Long.valueOf(1),Long.valueOf(1),"Task1",
				"to do my task 1",false,"Rahul",String.valueOf(now)));

		String jsonTodo = new ObjectMapper().writeValueAsString(toDoTaskList);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization","Bearer "+getJwt());
		HttpEntity<String> entity = new HttpEntity<>(jsonTodo, headers);
		ResponseEntity<String> result = restTemplate.postForEntity("/api/saveTodoList",
				entity, String.class);
		assertEquals(HttpStatus.CREATED, result.getStatusCode());

	}

	@Test
	@Order(4)
	public void getTodoListTest() throws Exception {
		updateToDoListTest();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization","Bearer "+getJwt());
		ResponseEntity<String> result=restTemplate.exchange("/api/getTodoList/1", HttpMethod.GET, new HttpEntity<>(headers), String.class);
		assertEquals(HttpStatus.ACCEPTED,result.getStatusCode());
	}
	@Test
	@Order(5)
	public void deleteTodoListTest() throws Exception {
		updateToDoListTest();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization","Bearer "+getJwt());
		ResponseEntity<String> result=restTemplate.exchange("/api/deleteTodoListT/1", HttpMethod.DELETE, new HttpEntity<>(headers), String.class);
		assertEquals(HttpStatus.OK,result.getStatusCode());
	}


	private <T> ArrayList<T> jsonArrayToList(String json, Class<T> targetClass) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		CollectionType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, targetClass);
		return mapper.readValue(json, type);
	}


	public String  getJwt() throws Exception {
		Authenticationrequest authenticationrequest = new Authenticationrequest("user","pass123");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Authenticationrequest> entity = new HttpEntity<>(authenticationrequest, headers);
		ResponseEntity<String> result = restTemplate.postForEntity("/authenticate",entity, String.class);
		JSONObject jsonObject= new JSONObject(result.getBody());
		jwtToken= jsonObject.getString("jwt");
		return jwtToken;
	}


}


