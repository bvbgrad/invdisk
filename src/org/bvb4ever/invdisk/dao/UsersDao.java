package org.bvb4ever.invdisk.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("usersDao")
public class UsersDao {

//	@Autowired
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	private SessionFactory sessionFactory;	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional
	public void create(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().save(user);
		return;
	}
	
	@Transactional
	public void resetPassword(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().saveOrUpdate(user);
		return;
	}
	
	public boolean exists(String username) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.idEq(username));
		User user = (User)crit.uniqueResult();
		return user != null;
	}
	
	public void updateUser(User user) {
		session().saveOrUpdate(user);
	}

	public void deleteUser(String username) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.idEq(username));
		User user = (User)crit.uniqueResult();
		session().delete(user);
		return;
	}
	
	public User getUser(String username) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.idEq(username));
		User user = (User)crit.uniqueResult();
		return user;
	}
	
	@Secured("ROLE_ADMIN")
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		return session().createQuery("from User").list();
	}
	
}
