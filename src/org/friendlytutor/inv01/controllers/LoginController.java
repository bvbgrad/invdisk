package org.friendlytutor.inv01.controllers;

import java.util.List;

import javax.validation.Valid;

import org.friendlytutor.inv01.dao.User;
import org.friendlytutor.inv01.dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	private UsersDao usersDao;
	@Autowired
	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}
	
	@RequestMapping("/admin")
	public String showLogin(Model model) {
		List<User> users = usersDao.getAllUsers();
		model.addAttribute("users", users);
		System.out.println("number of users = " + users.size());
		for (User user : users) {
			System.out.println(user);
		}
		
		return "admin";
	}
	
	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@RequestMapping("/logout")
	public String showLogout () {
		return "login";
	}
	
	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		
		model.addAttribute("user", new User());
		return "newaccount";
	}
	
	@RequestMapping(value="/createaccount", method=RequestMethod.POST)
	public String createAccount(@Valid User user, BindingResult result ) {
		
		if(result.hasErrors()) {
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
		
		return "accountcreated";
	}
}
