package org.friendlytutor.inv01.controllers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.friendlytutor.inv01.dao.Message;
import org.friendlytutor.inv01.dao.Offer;
import org.friendlytutor.inv01.dao.OffersDao;
import org.friendlytutor.inv01.dao.OffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonController {

	@Autowired
	private OffersService offersService; // if use data service layer
	
	@Autowired
	private OffersDao offersDao;  // if use DAO directly

	@RequestMapping(value="/error")
	public Offer checkError(HttpServletRequest req) {
		String sId = req.getParameter("id");
		int id = 0;
		if (sId != null) {
		     try {
	             id = Integer.parseInt(sId.trim());
	         }
	         catch(NumberFormatException e) {
	             System.out.println(e.toString());
	         }
         }
				
		Offer offer = offersDao.getOffer(id);
		System.out.println("offer #" + sId + " = " + offer);
		
		return offer;
	}
	
	@RequestMapping(value="/get")
	public Map<String, Object> getData(Model model) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("messages", "Message01");
		data.put("number", 1);
		return data;
	}

	@RequestMapping("/hello/{player}")
    public Message message(@PathVariable String player) {
        Message msg = new Message(player, "Hello " + player);
        return msg;
    }
	
	@RequestMapping(value="/offer")
	public Offer getOffer() {
		System.out.println("JsonController.getOffer");
		Offer offer = new Offer();
		try {
			offer = offersDao.getOffer(1);
		} catch (Exception e) {
			System.out.println("getCause: " + offer + " " + e.getCause());
			System.out.println("toString: " + e.toString());
		}
		
		System.out.println("Offer: " + offer);
		return offer;
	}
	
	@RequestMapping(value="/offers")
	public List<Offer> getOffers() {
		List<Offer> offers = new ArrayList<Offer>();
		offers = offersService.getOffersService();
		for (Offer offer : offers) {
			System.out.println(offer);
		}
		return offers;
	}
	
}
