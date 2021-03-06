package org.bvb4ever.invdisk.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.bvb4ever.invdisk.dao.PersistenceValidationGroup;
import org.bvb4ever.invdisk.dao.User;
import org.bvb4ever.invdisk.dao.UserValidator;
import org.bvb4ever.invdisk.dao.UsersDao;
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

		User user = usersDao.getUser(username);		
		model.addAttribute("user", user);

		return "editaccount";
	}
	
	@RequestMapping("/updateaccount")
	public String updateAccount(
			@ModelAttribute("user") User user,
			BindingResult result,
			Model model) {
		String sRole = 
			SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();		
		logger.info("updateAccount: "
				+ SecurityContextHolder.getContext().getAuthentication().getName()
				+ " " + sRole);
		
		   //Validation code
//	    validator.validate(user, result);
//
//		if (result.hasErrors()) {
//			System.out.println("result: " + result);
//	    }
//		
		System.out.println("update user: " + user);
		
		usersDao.updateUser(user);

// return a list of all users if acting as the Admin
		if(sRole == "ROLE_ADMIN") {
			List<User> users = usersDao.getAllUsers();
			model.addAttribute("users", users);
			return "admin";
		}

// default is to go to the home page
		return "invdisk";
	}
	
	@RequestMapping("/resetpassword")
	public String resetPassword(
			@RequestParam("userName") String username,
			Model model) {
		logger.info("resetPassword for '" + username + "' "
				+ SecurityContextHolder.getContext().getAuthentication().getName()
				+ " " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		
		User user = usersDao.getUser(username);		
		System.out.println("resetPassword for user: " + user);
		model.addAttribute("user", user);
		
		return "resetpassword";
	}
	
	@RequestMapping(value = "/updatepassword", method = RequestMethod.POST)
	public String updatePassword(
			@Validated(PersistenceValidationGroup.class) User user,
			BindingResult result,
			Model model) {
		String sRole = 
				SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		logger.info("updatePassword: "
				+ SecurityContextHolder.getContext().getAuthentication().getName()
				+ " " + sRole);
		
		if (result.hasErrors()) {
			System.out.println("update password error: " + result);
			return "resetpassword";
		}
		
		System.out.println("update user: " + user);
		
		usersDao.resetPassword(user);
		
		// return a list of all users if acting as the Admin
				if(sRole == "ROLE_ADMIN") {
					List<User> users = usersDao.getAllUsers();
					model.addAttribute("users", users);
					return "admin";
				}

		// default is to go to the home page
				return "invdisk";
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
