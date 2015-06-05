package org.friendlytutor.inv01.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.friendlytutor.inv01.dao.FormValidationGroup;
import org.friendlytutor.inv01.dao.User;
import org.friendlytutor.inv01.dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	private Logger logger = Logger.getLogger(LoginController.class);

	private UsersDao usersDao;

	@Autowired
	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	@RequestMapping("/admin")
	public String showAdmin(Model model) {
		logger.info("showAdmin: "
			+ SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		
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
			+ SecurityContextHolder.getContext().getAuthentication());
		return "login";
	}

	@RequestMapping("/logout")
	public String showLogout() {
		logger.info("showLogout: "
			+ SecurityContextHolder.getContext().getAuthentication());
		return "login";
	}

	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		logger.info("showNewAccount: "
			+ SecurityContextHolder.getContext().getAuthentication());

		model.addAttribute("user", new User());
		return "newaccount";
	}

	@RequestMapping(value = "/createaccount", method = RequestMethod.POST)
	public String createAccount(
			@Validated(FormValidationGroup.class) User user,
			BindingResult result) {
		logger.info("createAccount: "
			+ SecurityContextHolder.getContext().getAuthentication());

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
}
