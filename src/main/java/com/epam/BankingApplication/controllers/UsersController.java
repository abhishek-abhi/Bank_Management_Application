package com.epam.BankingApplication.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.epam.BankingApplication.exceptions.UserNotFoundException;
import com.epam.BankingApplication.exceptions.UsersNotFoundException;
import com.epam.BankingApplication.modals.User;
import com.epam.BankingApplication.services.UserDaoImpl;

/**
 * @author Abhishek_Kumar_Mandal
 *
 */
@RestController
public class UsersController {

	@Autowired
	UserDaoImpl service;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		Optional<List<User>> allUsers = Optional.ofNullable(service.retrieveAllUsers());
		if (allUsers.isPresent())
			return service.retrieveAllUsers();
		throw new UsersNotFoundException("Users are not found");
	}

	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable int id) {
		Optional<User> user = Optional.ofNullable(service.retrieveUserById(id));
		if (user.isPresent())
			return service.retrieveUserById(id);
		throw new UserNotFoundException("User having id - " + id + " not found");
	}

	/**
	 * @param user User
	 * @return ResponseEntity<Object>
	 */
	@PostMapping(path = "/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		service.addUser(user);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{id}")
				.buildAndExpand(user.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
}