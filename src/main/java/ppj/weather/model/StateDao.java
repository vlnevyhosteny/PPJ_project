package ppj.weather.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class StateDao {

    public static final String TABLE_NAME       = "state";

    public static final String ID_ATTRIBUTE     = "id";

    public static final String NAME_ATTRIBUTE   = "name";

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    public List<State> getStates() {
        String sql = "SELECT " + ID_ATTRIBUTE + "," + NAME_ATTRIBUTE + " FROM " + TABLE_NAME;

        return jdbc.query(sql, BeanPropertyRowMapper.newInstance(State.class));
    }

    public boolean update(State state) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(state);

        String sql = "UPDATE " + TABLE_NAME + " SET " + NAME_ATTRIBUTE + "=:name WHERE " + ID_ATTRIBUTE + "=:id";

        return jdbc.update(sql, params) == 1;
    }

    public boolean create(State state) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(state);

        String sql = "INSERT INTO " + TABLE_NAME + " (" + NAME_ATTRIBUTE + ") VALUES (:name)";

        return jdbc.update(sql, params) == 1;
    }

    @Transactional
    public int[] create(List<State> states) {
        SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(states.toArray());

        String sql = "INSERT INTO " + TABLE_NAME + " (" + NAME_ATTRIBUTE + ") VALUES (:name)";

        return jdbc.batchUpdate(sql, params);
    }

    public boolean delete(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource(ID_ATTRIBUTE, id);

        String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + ID_ATTRIBUTE + "=:id";

        return jdbc.update(sql, params) == 1;
    }


    public State getState(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource(ID_ATTRIBUTE, id);

        String sql = "SELECT " + ID_ATTRIBUTE + "," + NAME_ATTRIBUTE + " FROM " + TABLE_NAME+ " WHERE "
                + ID_ATTRIBUTE + "=:id";

        return jdbc.queryForObject(sql, params, BeanPropertyRowMapper.newInstance(State.class));
    }


    public void deleteStates() {
        String sql = "DELETE FROM " + TABLE_NAME;

        jdbc.getJdbcOperations().execute(sql);
    }
}
