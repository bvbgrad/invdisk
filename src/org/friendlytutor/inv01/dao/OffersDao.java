
package org.friendlytutor.inv01.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("offersDao")
public class OffersDao {

	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	
	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<Offer> getOffers() {
		Criteria crit = session().createCriteria(Offer.class);

		List<Offer> list = crit.list();
		System.out.println("OffersDao.getOffers() list size = " + list.size() );
		
		return list;
	}

	public void saveOrUpdate(Offer offer) {
		session().saveOrUpdate(offer);
	}

	public boolean delete(int id) {
		Query query = session().createQuery("delete from Offer where id=:id");
		query.setLong("id", id);
		return query.executeUpdate() == 1;
	}

	public Offer getOffer(int id) {
		Criteria crit = session().createCriteria(Offer.class);

		crit.add(Restrictions.idEq(id));

		return (Offer)crit.uniqueResult();
	}

// TODO Remove this method?  
// The rest of the class methods are hibernate enabled, except for this method
	public List<Offer> checkData() {
		System.out.println("OffersDao.checkData");
		return jdbc.query("select * from offers", new RowMapper<Offer>() {

			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Offer offer = new Offer();

				offer.setId(rs.getInt("id"));
				offer.setUsername(rs.getString("username"));
				offer.setText(rs.getString("text"));

				return offer;
			}
		});
	}


}
