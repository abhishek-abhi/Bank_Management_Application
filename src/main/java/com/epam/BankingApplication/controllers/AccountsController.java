package com.epam.BankingApplication.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.BankingApplication.exceptions.UserNotFoundException;
import com.epam.BankingApplication.modals.User;
import com.epam.BankingApplication.services.AccountsDaoService;
import com.epam.BankingApplication.services.UserDaoImpl;

/**
 * @author Abhishek_Kumar_Mandal
 *
 */
@RestController
public class AccountsController {

	private static final String SECOND_MESSAGE = " is not found";

	private static final String FIRST_MESSAGE = "User having id - ";

	@Autowired
	UserDaoImpl service;

	@Autowired
	AccountsDaoService accounts;

	/**
	 * @param id    Integer
	 * @param money double
	 */
	@PostMapping(path = "/users/withdraw/{id}/{money}")
	public void withdrawMoney(@PathVariable int id, @PathVariable double money) {
		Optional<User> user = Optional.ofNullable(service.retrieveUserById(id));
		if (user.isPresent())
			accounts.withdraw(user.get(), money);
		else
			throw new UserNotFoundException(FIRST_MESSAGE + id + SECOND_MESSAGE);
	}

	/**
	 * @param id    Integer
	 * @param money double
	 */
	@PostMapping(path = "/users/deposit/{id}/{money}")
	public void depositMoney(@PathVariable int id, @PathVariable double money) {
		Optional<User> user = Optional.ofNullable(service.retrieveUserById(id));
		if (user.isPresent())
			accounts.deposit(user.get(), money);
		else
			throw new UserNotFoundException(FIRST_MESSAGE + id + SECOND_MESSAGE);

	}

	/**
	 * @param firstId  Integer
	 * @param secondId Integer
	 * @param money    double
	 */
	@PostMapping(path = "/users/transfer/{firstId}/{secondId}/{money}")
	public void transferMoney(@PathVariable int firstId, @PathVariable int secondId, @PathVariable double money) {
		Optional<User> sender = Optional.ofNullable(service.retrieveUserById(firstId));
		if (!sender.isPresent())
			throw new UserNotFoundException(FIRST_MESSAGE + firstId + SECOND_MESSAGE);
		Optional<User> receiver = Optional.ofNullable(service.retrieveUserById(secondId));
		if (!receiver.isPresent())
			throw new UserNotFoundException(FIRST_MESSAGE + secondId + SECOND_MESSAGE);
		accounts.transfer(sender.get(), receiver.get(), money);

	}

}