package com.epam.BankingApplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.BankingApplication.exceptions.UserNotFoundException;
import com.epam.BankingApplication.modals.User;

/**
 * @author Abhishek_Kumar_Mandal
 *
 */
@Service
@Transactional
public class UserDaoImpl {

	private static final String SECOND_MESSAGE = " is not found";
	private static final String FIRST_MESSAGE = "User having id - ";
	@Autowired
	UserDaoService service;

	/**
	 * @return List<User>
	 */
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	/**
	 * @param id
	 * @return
	 */
	public User retrieveUserById(long id) {
		Optional<User> user = service.findById(id);
		if (user.isPresent())
			return user.get();
		throw new UserNotFoundException(FIRST_MESSAGE + id + SECOND_MESSAGE);
	}

	/**
	 * @param accountNumber String
	 * @return User
	 */
	public User retrieveUserByAccountNumber(String accountNumber) {
		List<User> users = retrieveAllUsers();
		for (User user : users) {
			if (user.getAccountNumber().equals(accountNumber)) {
				return user;
			}
		}
		throw new UserNotFoundException(accountNumber + SECOND_MESSAGE);
	}

	/**
	 * @param email    String
	 * @param password String
	 * @return User
	 */
	public User retrieveUserByEmailPassword(String email, String password) {
		List<User> users = retrieveAllUsers();
		for (User user : users) {
			String userEmail = user.getEmail();
			String userPassword = user.getPassword();
			if (userEmail.equals(email) && userPassword.equals(password)) {
				return user;
			}
		}
		throw new UserNotFoundException(email + SECOND_MESSAGE);
	}

	/**
	 * @param user User
	 */
	public void addUser(User user) {
		service.save(user);
	}

	/**
	 * @param id Long
	 */
	public void updateUser(long id) {
		Optional<User> user = service.findById(id);
		if (user.isPresent())
			service.save(user.get());
		throw new UserNotFoundException(FIRST_MESSAGE + id + SECOND_MESSAGE);
	}

	/**
	 * @param id Long
	 */
	public void deleteUser(long id) {
		Optional<User> user = service.findById(id);
		if (user.isPresent()) {
			service.delete(user.get());
		}
		throw new UserNotFoundException(FIRST_MESSAGE + id + SECOND_MESSAGE);
	}

}
