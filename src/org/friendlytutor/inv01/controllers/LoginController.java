package org.friendlytutor.inv01.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.friendlytutor.inv01.dao.PersistenceValidationGroup;
import org.friendlytutor.inv01.dao.User;
import org.friendlytutor.inv01.dao.UserValidator;
import org.friendlytutor.inv01.dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	private Logger logger = Logger.getLogger(LoginController.class);

	private UsersDao usersDao;
	
//	@Autowired
//    UserValidator validator;

	@Autowired
	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	@RequestMapping("/admin")
	public String showAdmin(Model model) {
		logger.info("showAdmin: "
			+ SecurityContextHolder.getContext().getAuthentication().getName()
			+ " " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		
		List<User> users = usersDao.getAllUsers();
		model.addAttribute("users", users);
		System.out.println("number of users = " + users.size());
		for (User user : users) {
			System.out.println(user);
		}

		return "admin";
	}

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String showLogin() {
		logger.info("showLogin: "
				+ SecurityContextHolder.getContext().getAuthentication().getName()
				+ " " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		return "login";
	}

	@RequestMapping("/logout")
	public String showLogout() {
		logger.info("showLogout: "
				+ SecurityContextHolder.getContext().getAuthentication().getName()
				+ " " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		return "logout";
	}

	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		logger.info("showNewAccount: "
				+ SecurityContextHolder.getContext().getAuthentication().getName()
				+ " " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());

		model.addAttribute("user", new User());
		return "newaccount";
	}

	@RequestMapping("/editaccount")
	public String editAccount(
			@RequestParam("userName") String username,
			Model model) {
		logger.info("editAccount: " + username + " "
				+ SecurityContextHolder.getContext().getAuthentication().getName()
				+ " " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		
		   //Validation code
//	    validator.validate(user, result);
//
//		if (result.hasErrors()) {
//			System.out.println("result: " + result);
//	    }
//				
		User user = usersDao.getUser(username);		
		model.addAttribute("user", user);

		return "editaccount";
	}
	
	@RequestMapping("/updateaccount")
	public String updateAccount(
			@ModelAttribute("user") User user,
			BindingResult result,
			Model model) {
		logger.info("updateAccount: "
				+ SecurityContextHolder.getContext().getAuthentication().getName()
				+ " " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		
		   //Validation code
//	    validator.validate(user, result);
//
//		if (result.hasErrors()) {
//			System.out.println("result: " + result);
//	    }
//		
		System.out.println("update user: " + user);
		
		usersDao.updateUser(user);
		
		List<User> users = usersDao.getAllUsers();
		model.addAttribute("users", users);
		return "admin";
	}
	
	@RequestMapping(value = "/createaccount", method = RequestMethod.POST)
	public String createAccount(
			@Validated(PersistenceValidationGroup.class) User user,
			BindingResult result) {
		logger.info("createAccount: "
				+ SecurityContextHolder.getContext().getAuthentication().getName()
				+ " " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());

		if (result.hasErrors()) {
			System.out.println("create account result error: " + result);
			return "newaccount";
		}

		user.setEnabled(true);
		user.setAuthority("ROLE_USER");
		System.out.println("Create account for: " + user);

		if (usersDao.exists(user.getUsername())) {
			System.out.println("Duplicate user");
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}

		try {
			usersDao.create(user);
		} catch (DuplicateKeyException e) {
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}

		logger.info("createAccount exit: " + user);
		return "accountcreated";
	}
	
	@RequestMapping(value = "/deleteaccount", method = RequestMethod.POST)
	public String deleteAccount(@RequestParam("userName") String username, Model model) {
		logger.info("deleteAccount: " + username + " "
				+ SecurityContextHolder.getContext().getAuthentication().getName()
				+ " " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		
		usersDao.deleteUser(username);			

		// return updated list of users
		List<User> users = usersDao.getAllUsers();
		model.addAttribute("users", users);
		
		return "admin";
	}
}
