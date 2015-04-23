package org.friendlytutor.inv01.test.tests;



import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:org/friendlytutor/inv01/config/dao-context.xml",
		"classpath:org/friendlytutor/inv01/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {

	@Test
	public void testCreateUser() {
		assertEquals("test stub", 1, 1);
	}
}
