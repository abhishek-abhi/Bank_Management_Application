package com.epam.BankingApplication.modals;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.epam.BankingApplication.resources.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Abhishek_Kumar_Mandal
 *
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7417109117481599175L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 4, message = "First Name must have atleast 4 characters")
	private String firstName;

	@Size(min = 4, message = "Last Name must have atleast 4 characters")
	private String lastName;
	@Email
	private String email;
	@JsonIgnore
	private String password;

	@NotNull
	@Size(min = 11, max = 11, message = "length of Account Number must be 11")
	private String accountNumber;

	@Enumerated(EnumType.STRING)
	private AccountType accountType;

	@JsonIgnore
	@NotNull
	private double balance;

	public User() {

	}

	/**
	 * @param id            Integer
	 * @param firstName     String
	 * @param lastName      String
	 * @param email         String
	 * @param accountNumber Integer
	 * @param accountType   Enum
	 */
	public User(Long id, String firstName, String lastName, String email, String password, String accountNumber,
			AccountType accountType, double balance) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
	}

	/**
	 * @param balance double
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * @return double
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @return Integer
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id Integer
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return AccountType enum
	 */
	public AccountType getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType enum
	 */
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	/**
	 * @return String
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName String
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return String
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName String
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email String
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return String
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber String
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("User [firstName=%s, lastName=%s, email=%s, accountNumber=%s, accountType=%s]", firstName,
				lastName, email, accountNumber, accountType);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (this.getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else {
			boolean accountNumberCheck = accountNumber.equals(other.accountNumber);
			if (!accountNumberCheck)
				return false;
		}

		if (email == null) {
			if (other.email != null)
				return false;
		} else {
			boolean emailCheck = email.equals(other.email);
			if (!emailCheck)
				return false;
		}

		return true;
	}

}