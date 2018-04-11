package ppj.weather.model;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class StateDao {

    public static final String TABLE_NAME       = "states";

    public static final String ID_ATTRIBUTE     = "id";

    public static final String NAME_ATTRIBUTE   = "name";

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    public List<State> getStates() {
        Criteria criteria = session().createCriteria(State.class);

        criteria.createAlias("state", "s")
                .add(Restrictions.eq("s.enabled", true));

        return criteria.list();
    }

    public void saveOrUpdate(State state) {
        session().saveOrUpdate(state);
    }

    public void saveOrUpdate(List<State> states) {
        Session session = session();

        for (State state:
             states) {
            session.saveOrUpdate(state);
        }
    }

    public boolean delete(int id) {
        Query query = session()
                .createQuery("DELETE FROM " + TABLE_NAME + " WHERE " + ID_ATTRIBUTE + "=:id");
        query.setInteger("id", id);

        return query.executeUpdate() == 1;
    }


    public State getState(int id) {
        Criteria criteria = session().createCriteria(State.class);

        criteria.createAlias("state", "s");

        criteria.add(Restrictions.eq("s.enabled", true));
        criteria.add(Restrictions.idEq(id));

        return (State) criteria.uniqueResult();
    }

    public void deleteOffers() {
        session().createQuery("delete from " + TABLE_NAME).executeUpdate();
    }
}
