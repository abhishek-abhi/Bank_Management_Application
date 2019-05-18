package com.epam.BankingApplication.controllers;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.BankingApplication.modals.User;
import com.epam.BankingApplication.resources.AccountType;
import com.epam.BankingApplication.services.AccountsDaoImpl;
import com.epam.BankingApplication.services.UserDaoImpl;

/**
 * @author Abhishek_Kumar_Mandal
 *
 */
@Controller
public class ViewsController {

	public static String userNameWelcome = "";
	@Autowired
	UserDaoImpl service;

	@Autowired
	AccountsDaoImpl accounts;

	@GetMapping("/")
	public String homePage() {
		return "redirect:/index";
	} 

	@GetMapping("/index")
	public String welcomePage(Model model) {
		model.addAttribute("username", "");
		model.addAttribute("password", "");
		return "index";
	}

	@PostMapping("/index")
	public String welcomePagePOST(@ModelAttribute("username") String username,
			@ModelAttribute("password") String password) {
		User user = service.retrieveUserByEmailPassword(username.trim(), password.trim());
		userNameWelcome = user.getFirstName() + user.getLastName();
		return "redirect:/deposit";
	}

	@GetMapping("/logout")
	public String logoutPage() {
		userNameWelcome = "";
		return "redirect:/";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("firstName", "");
		model.addAttribute("lastName", "");
		model.addAttribute("email", "");
		model.addAttribute("accountType");
		model.addAttribute("password");
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signupPost(@ModelAttribute("firstName") String firstName, @ModelAttribute("lastName") String lastName,
			@ModelAttribute("email") String email, @ModelAttribute("password") String password,
			@ModelAttribute("accountType") String accountType) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		String accountNumber = "" + random.nextLong(10_000_000_000L, 100_000_000_000L);
		User user = new User();
		user.setFirstName(firstName.trim());
		user.setLastName(lastName.trim());
		user.setAccountNumber(accountNumber.trim());
		user.setEmail(email.trim());
		user.setAccountType(AccountType.valueOf(accountType.trim()));
		user.setPassword(password.trim());
		service.addUser(user);
		userNameWelcome = "";
		return "redirect:/";
	}

	@GetMapping("/deposit")
	public String deposit(Model model) {
		if (userNameWelcome.equals("")) {
			return "redirect:/";
		}
		model.addAttribute("accountType", "");
		model.addAttribute("amount", "");
		model.addAttribute("accountNumber", "");
		return "deposit";
	}

	@RequestMapping(value = "/deposit", method = RequestMethod.POST)
	public String depositPOST(@ModelAttribute("amount") String amount,
			@ModelAttribute("accountType") String accountType, @ModelAttribute("accountNumber") String accountNumber) {
		if (userNameWelcome.equals("")) {
			return "redirect:/";
		}
		double money = Double.parseDouble(amount);
		User user = service.retrieveUserByAccountNumber(accountNumber.trim());
		accounts.deposit(user, money);
		userNameWelcome = "";
		return "redirect:/";
	}

	@GetMapping("/withdraw")
	public String withdraw(Model model) {
		if (userNameWelcome.equals("")) {
			return "redirect:/";
		}
		model.addAttribute("accountType", "");
		model.addAttribute("amount", "");
		model.addAttribute("accountNumber", "");
		return "withdraw";
	}

	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
	public String withdrawPOST(@ModelAttribute("amount") String amount,
			@ModelAttribute("accountType") String accountType, @ModelAttribute("accountNumber") String accountNumber) {
		if (userNameWelcome.equals("")) {
			return "redirect:/";
		}
		double money = Double.parseDouble(amount);
		User user = service.retrieveUserByAccountNumber(accountNumber.trim());
		accounts.withdraw(user, money);
		userNameWelcome = "";
		return "redirect:/";
	}

	@GetMapping("/transfer")
	public String transfer(Model model) {
		if (userNameWelcome.equals("")) {
			return "redirect:/";
		}
		model.addAttribute("firstAccountNumber", "");
		model.addAttribute("secondAccountNumber", "");
		model.addAttribute("amount", "");
		return "betweenAccounts";
	}

	@PostMapping("/transfer")
	public String transferPOST(@ModelAttribute("firstAccountNumber") String firstAccountNumber,
			@ModelAttribute("secondAccountNumber") String secondAccountNumber,
			@ModelAttribute("amount") String amount) {
		if (userNameWelcome.equals("")) {
			return "redirect:/";
		}
		User firstUser = service.retrieveUserByAccountNumber(firstAccountNumber.trim());
		User secondUser = service.retrieveUserByAccountNumber(secondAccountNumber.trim());
		double money = Double.parseDouble(amount);
		accounts.transfer(firstUser, secondUser, money);
		userNameWelcome = "";
		return "redirect:/";
	}
}
