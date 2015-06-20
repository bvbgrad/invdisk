package org.friendlytutor.inv01.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.friendlytutor.inv01.dao.Offer;
import org.friendlytutor.inv01.dao.OffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Inv01Controller {

	private Logger logger = Logger.getLogger(Inv01Controller.class);
	
	private OffersService offersService;
	@Autowired
	public void setOffersService(OffersService offersService) {
		this.offersService = offersService;
	}
	
//	@Secured("ROLE_VIEWER")
	@RequestMapping("/")
	public String showHome (Model model) {
		logger.info("showHome: "
				+ SecurityContextHolder.getContext().getAuthentication().getDetails());
		logger.info("showHome: "
				+ SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        logger.info("showHome: "
    			+ SecurityContextHolder.getContext().getAuthentication().getName()
    			+ " " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		return "inv01";
	}
	
	@RequestMapping("/showoffers")
	public String showOffers (Model model) {
        logger.info("showOffers: "
    			+ SecurityContextHolder.getContext().getAuthentication().getName()
    			+ " " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());

		List<Offer> offers = offersService.getOffersService();
		model.addAttribute("offers", offers);
		return "showoffers";
	}
	
}
