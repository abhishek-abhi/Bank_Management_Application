package com.epam.BankingApplication.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.BankingApplication.modals.User;

/**
 * @author Abhishek_Kumar_Mandal
 *
 */
@Service
public class AccountsDaoImpl {
	private static final Logger LOG = LoggerFactory.getLogger(AccountsDaoService.class);

	@Autowired
	UserDaoService service;

	@Autowired
	AccountsDaoService accounts;

	/**
	 * @param user  User
	 * @param money double
	 */
	public void withdraw(User user, double money) {
		LOG.info(user.getFirstName() + " " + user.getLastName() + " withdrawing money : " + money);
		accounts.withdraw(user, money);
		service.save(user);
	}

	/**
	 * @param user  User
	 * @param money double
	 */
	public void deposit(User user, double money) {
		LOG.info(user.getFirstName() + " " + user.getLastName() + " depositing money: " + money);
		accounts.deposit(user, money);
		service.save(user);
	}

	/**
	 * @param firstUser  User
	 * @param secondUser User
	 * @param money      double
	 */
	public void transfer(User firstUser, User secondUser, double money) {
		LOG.info(firstUser.getFirstName() + " " + firstUser.getLastName() + " transferring money : " + money + " to "
				+ secondUser.getFirstName() + " " + secondUser.getLastName());
		double firstBalance = firstUser.getBalance();
		accounts.transfer(firstUser, secondUser, money);
		service.save(firstUser);
		service.save(secondUser);
	}
}
