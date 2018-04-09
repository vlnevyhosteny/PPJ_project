package ppj.weather.model;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CityDao {

    public static final String TABLE_NAME = "city";

    public static final String ID_ATTRIBUTE = "id";

    public static final String NAME_ATTRIBUTE = "name";

    public static final String STATE_ATTRIBUTE = "stateId";

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    public List<City> getCities() {
        Criteria criteria = session().createCriteria(City.class);

        criteria.createAlias("city", "c", JoinType.INNER_JOIN)
                .add(Restrictions.eq("c.enabled", true));

        return criteria.list();
    }

    public void saveOrUpdate(City city) {
        session().saveOrUpdate(city);
    }

    public void saveOrUpdate(List<City> cities) {
        Session session = session();

        for (City city:
                cities) {
            session.saveOrUpdate(city);
        }
    }

    public boolean delete(int id) {
        Query query = session()
                .createQuery("DELETE FROM " + TABLE_NAME + " WHERE " + ID_ATTRIBUTE + "=:id");

        query.setLong("id", id);

        return query.executeUpdate() == 1;
    }


    public City getCity(int id) {
        Criteria crit = session().createCriteria(City.class);

        crit.createAlias("city", "c");

        crit.add(Restrictions.eq("c.enabled", true));
        crit.add(Restrictions.idEq(id));

        return (City) crit.uniqueResult();
    }


    public void deleteOffers() {
        session().createQuery("delete from " + TABLE_NAME).executeUpdate();
    }

}
