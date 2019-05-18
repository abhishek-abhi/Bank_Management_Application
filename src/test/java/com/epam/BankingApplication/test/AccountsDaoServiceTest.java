package com.epam.BankingApplication.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.epam.BankingApplication.modals.User;
import com.epam.BankingApplication.resources.AccountType;
import com.epam.BankingApplication.services.AccountsDaoService;

/**
 * @author Abhishek_Kumar_Mandal
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountsDaoServiceTest {

	private static final Logger LOG = LoggerFactory.getLogger(AccountsDaoServiceTest.class);

	@MockBean
	AccountsDaoService service;

	User firstUser = new User(1L, "Abhishek Kumar", "Mandal", "Abhishek_Kumar_Mandal@epam.com", "abhi", "12365896325",
			AccountType.SAVINGS, 100);
	User secondUser = new User(2L, "Alia", "Bhatt", "Alia_Bhatt@example.com", "alia", "12326496325",
			AccountType.SAVINGS, 200);

	@Before
	@Test
	public void setUp() {
		LOG.info("StartUp Test");
		firstUser.setBalance(4000);
		secondUser.setBalance(3000);
		service = new AccountsDaoService();
	}

	@Test
	public void withdrawMoneyTest() {
		LOG.info("Withdraw Money Test");
		service.withdraw(firstUser, 350);
		assertThat(firstUser.getBalance(), is(3650.0));
	}

	@Test
	public void depositMoneyTest() {
		LOG.info("Deposit Money Test");
		service.deposit(firstUser, 350);
		assertThat(firstUser.getBalance(), is(4350.0));
	}

	@Test
	public void transferMoneyTest() {
		LOG.info("Transfer Money Test");
		service.transfer(firstUser, secondUser, 350);
		assertThat(firstUser.getBalance(), is(3650.0));
		assertThat(secondUser.getBalance(), is(3350.0));
	}
}