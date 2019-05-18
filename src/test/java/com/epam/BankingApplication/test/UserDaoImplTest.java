package com.epam.BankingApplication.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.BankingApplication.modals.User;
import com.epam.BankingApplication.resources.AccountType;
import com.epam.BankingApplication.services.UserDaoImpl;

/**
 * @author Abhishek_Kumar_Mandal
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UserDaoImplTest {

	private static final Logger LOG = LoggerFactory.getLogger(UserDaoImplTest.class);

	@Mock
	UserDaoImpl service;

	List<User> users = new ArrayList<User>();

	@Before
	@Test
	public void setUp() {
		LOG.info("startUp Test");
		users.add(new User(1L, "Abhishek Kumar", "Mandal", "Abhishek_Kumar_Mandal@epam.com", "abhi", "12345678910",
				AccountType.SAVINGS, 100));
		users.add(new User(2L, "Alia", "Bhatt", "alia_bhatt@example.com", "13345678930", "alia", AccountType.CURRENT,
				200));
	}

	@Test
	public void retrieveAllUsersTest() {
		LOG.info("Retrieve All Users Test");
		given(service.retrieveAllUsers()).willReturn(users);
		assertThat(service.retrieveAllUsers().size(), is(2));
	}

	@Test
	public void retrieveUserByIdTest() {
		LOG.info("Retrieve User By Id Test");
		User user = new User(1L, "Abhishek Kumar", "Mandal", "Abhishek_Kumar_Mandal@epam.com", "abhi", "12345678910",
				AccountType.SAVINGS, 100);
		given(service.retrieveUserById(1)).willReturn(user);
		assertThat(service.retrieveUserById(1).getAccountNumber(), is("12345678910"));
	}

	@Test
	public void retrieveUserByAccountNumber() {
		LOG.info("Retrieve User By Account Number Test");
		User user = new User(1L, "Abhishek Kumar", "Mandal", "Abhishek_Kumar_Mandal@epam.com", "abhi", "12345678910",
				AccountType.SAVINGS, 100);
		given(service.retrieveUserByAccountNumber("12345678910")).willReturn(user);
		assertThat(service.retrieveUserByAccountNumber("12345678910").getEmail(), is("Abhishek_Kumar_Mandal@epam.com"));
	}

	@Test
	public void retrieveUserByEmailPassword() {
		LOG.info("Retrieve User By Email and Password Test");
		User user = new User(1L, "Abhishek Kumar", "Mandal", "Abhishek_Kumar_Mandal@epam.com", "abhi", "12345678910",
				AccountType.SAVINGS, 100);
		String email = "Abhishek_Kumar_Mandal@epam.com";
		String password = "abhi";
		given(service.retrieveUserByEmailPassword(email, password)).willReturn(user);
		assertThat(service.retrieveUserByEmailPassword(email, password).getAccountNumber(), is("12345678910"));
	}

	@Test
	public void addUserTest() {
		LOG.info("Add user Test");
		User user = new User(3L, "Sachin", "Tendulkar", "Sachin_Tendulkar@example.com", "sachin", "85697458253",
				AccountType.CURRENT, 200);
		users.add(user);
		given(service.retrieveAllUsers()).willReturn(users);
		assertThat(service.retrieveAllUsers().size(), is(3));
	}

	@Test
	public void updateUserTest() {
		LOG.info("Update User Test");
		User user = new User(1L, "Abhishek Kumar", "Mandal", "Abhishek_Kumar_Mandal@epam.com", "abhi", "12345678910",
				AccountType.SAVINGS, 100);
		given(service.retrieveUserById(1)).willReturn(user);
		user.setBalance(15000);
		assertThat(service.retrieveUserById(1).getBalance(), is(15000.0));
	}

	@Test(expected = NullPointerException.class)
	public void deleteUserTest() {
		LOG.info("Delete User Test");
		service = new UserDaoImpl();
		User user = new User(1L, "Abhishek Kumar", "Mandal", "Abhishek_Kumar_Mandal@epam.com", "abhi", "12345678910",
				AccountType.SAVINGS, 100);
		given(service.retrieveUserById(1L)).willReturn(user);
		service.deleteUser(1L);
		assertThat(service.retrieveUserById(1L), is(user));

	}

}