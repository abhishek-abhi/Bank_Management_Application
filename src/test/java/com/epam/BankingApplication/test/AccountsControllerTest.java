package com.epam.BankingApplication.test;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.epam.BankingApplication.controllers.AccountsController;
import com.epam.BankingApplication.modals.User;
import com.epam.BankingApplication.resources.AccountType;
import com.epam.BankingApplication.services.AccountsDaoService;
import com.epam.BankingApplication.services.UserDaoImpl;

/**
 * @author Abhishek_Kumar_Mandal
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(AccountsController.class)
public class AccountsControllerTest {

	private static final Logger LOG = LoggerFactory.getLogger(AccountsControllerTest.class);

	@Autowired
	MockMvc mockMvc;

	@MockBean
	AccountsDaoService accounts;

	@MockBean
	UserDaoImpl service;

	/**
	 * Setting up Test
	 */
	@Before
	@Test
	public void setUp() {
		LOG.info("StartUp Test");
		User firstUser = new User(1L, "Abhishek Kumar", "Mandal", "Abhishek_Kumar_Mandal@epam.com", "abhi",
				"12345678910", AccountType.SAVINGS, 100);
		given(service.retrieveUserById(1)).willReturn(firstUser);
		User secondUser = new User(2L, "Alia", "Bhatt", "alia_bhatt@example.com", "alia", "13345678930",
				AccountType.CURRENT, 200);
		given(service.retrieveUserById(2)).willReturn(secondUser);
		accounts.deposit(firstUser, 4500);
		accounts.deposit(secondUser, 2600);
	}

	@Test
	public void withdrawMoneyTest() throws Exception {
		LOG.info("Withdraw Money Test");
		RequestBuilder request = MockMvcRequestBuilders.post("/users/withdraw/1/2000")
				.accept(MediaType.APPLICATION_JSON).content("").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk()).andReturn();
	}

	@Test
	public void depositMoneyTest() throws Exception {
		LOG.info("Deposit Money Test");
		RequestBuilder request = MockMvcRequestBuilders.post("/users/deposit/1/2000").accept(MediaType.APPLICATION_JSON)
				.content("").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk()).andReturn();
	}

	@Test
	public void transferMoneyTest() throws Exception {
		LOG.info("Transfer Money Test");
		RequestBuilder request = MockMvcRequestBuilders.post("/users/transfer/1/2/200")
				.accept(MediaType.APPLICATION_JSON).content("").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk()).andReturn();
	}
}