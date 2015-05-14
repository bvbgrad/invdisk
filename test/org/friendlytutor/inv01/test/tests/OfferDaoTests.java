package org.friendlytutor.inv01.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.sql.DataSource;

import org.friendlytutor.inv01.dao.Offer;
import org.friendlytutor.inv01.dao.OffersDao;
import org.friendlytutor.inv01.dao.User;
import org.friendlytutor.inv01.dao.UsersDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:org/friendlytutor/inv01/config/dao-context.xml",
		"classpath:org/friendlytutor/inv01/test/config/datasource.xml" })
//		"classpath:com/caveofprogramming/spring/web/config/security-context.xml",
@RunWith(SpringJUnit4ClassRunner.class)
public class OfferDaoTests {
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private OffersDao offersDao;
	
	@Autowired
	private DataSource dataSource;

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		
		jdbc.execute("delete from authorities");
		jdbc.execute("delete from offers");
		jdbc.execute("delete from users");
	}
	
	@Test
	public void testCreateOffer() {
		User user = new User("user01", "password", "brent", "user email", true, "ADMIN");
		usersDao.create(user);
		
		Offer offer = new Offer("user01", "This is a test offer.");
		
		assertTrue("Offer creation should return true", offersDao.create(offer));
		
		List<Offer> offers = offersDao.getOffers();
		
		assertEquals("Should be one offer in database.", 1, offers.size());
		
		assertEquals("Retrieved offer should match created offer.", offer, offers.get(0));
		
		// Get the offer with ID filled in.
		offer = offers.get(0);
		
		offer.setText("Updated offer text.");
		assertTrue("Offer update should return true", offersDao.update(offer));
		
		Offer updated = offersDao.getOffer(offer.getId());
		
		assertEquals("Updated offer should match retrieved updated offer", offer, updated);
		
		offersDao.delete(offer.getId());
		
		List<Offer> empty = offersDao.getOffers();
		
		assertEquals("Offers lists should be empty.", 0, empty.size());
	}
	
}
