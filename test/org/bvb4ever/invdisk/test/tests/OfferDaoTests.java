package org.bvb4ever.invdisk.test.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.sql.DataSource;

import org.bvb4ever.invdisk.dao.Offer;
import org.bvb4ever.invdisk.dao.OffersDao;
import org.bvb4ever.invdisk.dao.User;
import org.bvb4ever.invdisk.dao.UsersDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:org/bvb4ever/invdisk/config/dao-context.xml",
		"classpath:org/bvb4ever/invdisk/config/security-context.xml",
		"classpath:org/bvb4ever/invdisk/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class OfferDaoTests {

	@Autowired
	private OffersDao offersDao;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private DataSource dataSource;
	
	private User user1 =
			new User("user01", "password", "brent", "user@email.com", true, "ROLE_ADMIN");
	private User user2 =
			new User("user02", "password", "brent", "user@email.com", true, "ROLE_USER");
	private User user3 =
			new User("user03", "password", "brent", "user@email.com", true, "");
	private User user4 =
			new User("user04", "password", "brent", "user@email.com", false, "ROLE_USER");

	private Offer offer1 = new Offer(user1, "This is a test offer.");
	private Offer offer2 = new Offer(user1, "This is another test offer.");
	private Offer offer3 = new Offer(user2, "This is yet another test offer.");
	private Offer offer4 = new Offer(user3, "This is a test offer once again.");
	private Offer offer5 = new Offer(user3, "Here is an interesting offer of some kind.");
	private Offer offer6 = new Offer(user3, "This is just a test offer.");
	private Offer offer7 = new Offer(user4, "This is a test offer for a user that is not enabled.");

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offer");
		jdbc.execute("delete from user");
	}
	
	@Test
	public void testGetusername() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		
		offersDao.saveOrUpdate(offer1);
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);

		List<Offer> offers1 = offersDao.getOffers(user3.getUsername());
		assertEquals("Should be three offers for this user.", 3, offers1.size());

		List<Offer> offers2 = offersDao.getOffers("lkjlkj");
		assertEquals("Should be zero offers non-existent user.", 0, offers2.size());

		List<Offer> offers3 = offersDao.getOffers(user2.getUsername());
		assertEquals("Should be one offer for this user.", 1, offers3.size());
	}
	
	@Test
	public void testCreateRetrieve() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		
		offersDao.saveOrUpdate(offer1);
		
		List<Offer> offers1 = offersDao.getOffers();
		assertEquals("Should be one offer.", 1, offers1.size());
		
		assertEquals("Retrieved offer should equal inserted offer.", offer1, offers1.get(0));
		
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);
		
		List<Offer> offers2 = offersDao.getOffers();
		assertEquals("Should be six offers for enabled users.", 6, offers2.size());
	}

	@Test
	public void testUpdate() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		offersDao.saveOrUpdate(offer1);
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);
		
		offer3.setText("This offer has updated text.");
		offersDao.saveOrUpdate(offer3);

		Offer retrieved = offersDao.getOffer(offer3.getId());
		assertEquals("Retrieved offer should be updated.", retrieved, offer3);
	}
	
	public void testDelete() {
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		offersDao.saveOrUpdate(offer1);
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);
		
		Offer retrieved1 = offersDao.getOffer(offer2.getId());
		Assert.assertNotNull("Offer with ID " + retrieved1.getId() + " should not be null",
			retrieved1);
				
		offersDao.delete(offer2.getId());
		
		Offer retrieved2 = offersDao.getOffer(offer2.getId());
		Assert.assertNull("Offer with ID " + retrieved2.getId() + " should not be null",
				retrieved1);
	}
	
	@Test
	public void testOffers() {

		usersDao.create(user1);

		Offer offer = new Offer(user1, "This is a test offer.");

		offersDao.saveOrUpdate(offer);

		List<Offer> offers = offersDao.getOffers();

		assertEquals("Should be one offer in database.", 1, offers.size());

		assertEquals("Retrieved offer should match created offer.", offer,
				offers.get(0));

		// Get the offer with ID filled in.
		offer = offers.get(0);

		offer.setText("Updated offer text.");
		
		offersDao.saveOrUpdate(offer);

		Offer updated = offersDao.getOffer(offer.getId());

		assertEquals("Updated offer should match retrieved updated offer",
				offer, updated);

		// Test get by ID ///////
		Offer offer2 = new Offer(user1, "This is a test offer.");

		offersDao.saveOrUpdate(offer2);
		
		List<Offer> userOffers = offersDao.getOffers(user1.getUsername());
		assertEquals("Should be two offers for user.", 2, userOffers.size());
		
		List<Offer> secondList = offersDao.getOffers();
		
		for(Offer current: secondList) {
			Offer retrieved = offersDao.getOffer(current.getId());
			
			assertEquals("Offer by ID should match offer from list.", current, retrieved);
		}
		
		// Test deletion
		offersDao.delete(offer.getId());

		List<Offer> finalList = offersDao.getOffers();

		assertEquals("Offers lists should contain one offer.", 1, finalList.size());
	}

}
