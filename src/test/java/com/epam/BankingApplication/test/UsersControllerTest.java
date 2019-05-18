package com.epam.BankingApplication.test;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.epam.BankingApplication.controllers.UsersController;
import com.epam.BankingApplication.modals.User;
import com.epam.BankingApplication.resources.AccountType;
import com.epam.BankingApplication.services.UserDaoImpl;

/**
 * @author Abhishek_Kumar_Mandal
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UsersController.class)
public class UsersControllerTest {

	private static final Logger LOG = LoggerFactory.getLogger(UsersControllerTest.class);

	@Autowired
	MockMvc mockMvc;

	@MockBean
	UserDaoImpl service;

	List<User> users = new ArrayList<User>();

	@Before
	@Test
	public void setUp() {
		LOG.info("StartUp Test");
		users.add(new User(1L, "Abhishek Kumar", "Mandal", "Abhishek_Kumar_Mandal@epam.com", "abhi", "12345678910",
				AccountType.SAVINGS, 10000));
		users.add(new User(2L, "Alia", "Bhatt", "Alia_Bhatt@example.com", "alia", "13345678930", AccountType.CURRENT,
				20000));
		given(service.retrieveAllUsers()).willReturn(users);
		given(service.retrieveUserById(1)).willReturn(users.get(0));

	}

	@Test
	public void getAllUsersTest() throws Exception {

		LOG.info("Get All Users Test");
		RequestBuilder request = MockMvcRequestBuilders.get("/users").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().json(
						"[\n" + "{\n" + "id: 1,\n" + "firstName: \"Abhishek Kumar\",\n" + "lastName: \"Mandal\",\n"
								+ "email: \"Abhishek_Kumar_Mandal@epam.com\",\n" + "accountNumber: \"12345678910\",\n"
								+ "accountType: \"SAVINGS\"\n" + "},\n" + "{\n" + "id: 2,\n" + "firstName: \"Alia\",\n"
								+ "lastName: \"Bhatt\",\n" + "email: \"Alia_Bhatt@example.com\",\n"
								+ "accountNumber: \"13345678930\",\n" + "accountType: \"CURRENT\"\n" + "}\n" + "]"))
				.andReturn();
	}

	@Test
	public void getUserByIdTest() throws Exception {
		LOG.info("Get user by id Test");
		RequestBuilder request = MockMvcRequestBuilders.get("/users/1").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().json("{\n" + "id: 1,\n" + "firstName: \"Abhishek Kumar\",\n"
						+ "lastName: \"Mandal\",\n" + "email: \"Abhishek_Kumar_Mandal@epam.com\",\n"
						+ "accountNumber: \"12345678910\",\n" + "accountType: \"SAVINGS\"\n" + "}"))
				.andReturn();
	}

	@Test
	public void createUser() throws Exception {
		LOG.info("Create User Test");
		RequestBuilder request = MockMvcRequestBuilders.post("/users").accept(MediaType.APPLICATION_JSON).content(
				"{\"firstName\":\"Sachin\",\"lastName\":\"Tendulkar\",\"email\":\"sachin_Tendulkar@example.com\",\"accountNumber\":\"12345685216\",\"accountType\":\"SAVINGS\",\"password\":\"pass\"}")
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isCreated()).andReturn();
	}
}