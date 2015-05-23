package org.friendlytutor.inv01.test.tests;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

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
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {

	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private DataSource dataSource;
	
	private User user1 =
			new User("user01", "password", "brent", "user@email.com", true, "ADMIN");
	private User user2 =
			new User("user02", "password", "brent", "user@email.com", true, "ADMIN");
	private User user3 =
			new User("user03", "password", "brent", "user@email.com", true, "ADMIN");
	private User user4 =
			new User("user04", "password", "brent", "user@email.com", true, "ADMIN");
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from authorities");	
		jdbc.execute("delete from users");	
	}
	
	
	
	@Test
	public void testCreateRetrieve() {
		usersDao.create(user1);
		List<User> users = usersDao.getAllUsers();
		assertEquals("One user should have been created and retrieved",
			1, users.size());
		assertEquals("Inserted user should match retrieved user",
			user1, users.get(0));
		
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		users = usersDao.getAllUsers();
		assertEquals("Four users should have been created and retrieved",
				4, users.size());
		
	}
	
	@Test
	public void testCreateUser() {
		User user = new User("user01", "password", "brent", "user@email.com", true, "ADMIN");
		
		usersDao.create(user);
		
		List<User> users = usersDao.getAllUsers();
		
		assertEquals("Number of users should be 1.", 1, users.size() );
		
		assertTrue("User should exist", usersDao.exists(user.getUsername()));
		
		assertEquals("Created user be identical to retrieved user", user, users.get(0));	
	}
}
