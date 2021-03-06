package org.bvb4ever.invdisk.test.tests;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.bvb4ever.invdisk.dao.User;
import org.bvb4ever.invdisk.dao.UsersDao;
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
		"classpath:org/bvb4ever/invdisk/config/dao-context.xml",
		"classpath:org/bvb4ever/invdisk/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {

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
			new User("user04", "password", "brent", "user@email.com", true, "ROLE_USER");
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offer");
		jdbc.execute("delete from user");	
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
	public void testExists() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		assertTrue("User should exist", usersDao.exists(user2.getUsername()));
		assertFalse("User should not exist", usersDao.exists("lkjl"));
	}
	
	@Test
	public void testDelete() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		
		List<User> users = usersDao.getAllUsers();
		assertEquals("Four users should have been created and retrieved",
				4, users.size());
		
		String sUser3username = user3.getUsername();
		assertTrue("User should exist", usersDao.exists(sUser3username));
		
		usersDao.deleteUser(sUser3username);
		
		assertFalse("User should not exist", usersDao.exists(sUser3username));
		
		users = usersDao.getAllUsers();
		assertEquals("Now there should be three users", 3, users.size());
		
	}
	
}
