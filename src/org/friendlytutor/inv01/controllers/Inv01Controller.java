package org.friendlytutor.inv01.controllers;

import java.util.List;

import org.friendlytutor.inv01.dao.Offer;
import org.friendlytutor.inv01.dao.OffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
		System.out.println("\nRun: " + new java.util.Date());
		List<Offer> offers = offersService.getOffersService();
		model.addAttribute("offers", offers);
		System.out.println("number of offers = " + offers.size());
		for (Offer offer : offers) {
			System.out.println(offer);
		}
		
		String sUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("username", sUsername);
		return "inv01";
	}
	
}
