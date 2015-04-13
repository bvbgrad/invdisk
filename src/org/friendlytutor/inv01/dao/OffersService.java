package org.friendlytutor.inv01.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OffersService {

	@Autowired
	private OffersDao offersDao;
	
	public void setOffersDao(OffersDao offersDao) {
		this.offersDao = offersDao;
	}

	public List<Offer> getOffersService() {
		return offersDao.getOffers();
	}
	
}
