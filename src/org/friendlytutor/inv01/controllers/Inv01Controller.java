package org.friendlytutor.inv01.controllers;

import java.util.List;

import org.friendlytutor.inv01.dao.Offer;
import org.friendlytutor.inv01.dao.OffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Inv01Controller {

	private OffersService offersService;
	
	@Autowired
	public void setOffersService(OffersService offersService) {
		this.offersService = offersService;
	}
	

	@RequestMapping("/")
	public String showHome (Model model) {
		List<Offer> offers = offersService.getOffersService();
		model.addAttribute("offers", offers);
		return "inv01";
	}
	
	@RequestMapping("/login")
	public String showLogin () {
		return "login";
	}
	
	
}
