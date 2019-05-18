package com.epam.BankingApplication.exceptions;

/**
 * @author Abhishek_Kumar_Mandal
 *
 */
public class UserNotFoundException extends RuntimeException {

	/**
	 * @param message
	 */
	public UserNotFoundException(String message) {
		super(message);
	}

}