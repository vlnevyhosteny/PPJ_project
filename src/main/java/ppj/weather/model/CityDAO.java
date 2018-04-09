package ppj.weather.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CityDAO {

    public static final String TABLE_NAME = "city";

    public static final String ID_ATTRIBUTE = "id";

    public static final String NAME_ATTRIBUTE = "name";

    public static final String STATE_ATTRIBUTE = "stateId";

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    public List<City> getCities() {
        String sql = "SELECT " + ID_ATTRIBUTE + "," + NAME_ATTRIBUTE + "," + STATE_ATTRIBUTE
                + " FROM " + TABLE_NAME;

        return jdbc.query(sql, BeanPropertyRowMapper.newInstance(City.class));
    }

    public boolean update(City city) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(city);

        String sql = "UPDATE " + TABLE_NAME + " SET " + NAME_ATTRIBUTE + "=:name , " + STATE_ATTRIBUTE + "=:stateId ,"
                + " WHERE " + ID_ATTRIBUTE + "=:id";

        return jdbc.update(sql, params) == 1;
    }

    public boolean create(City city) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(city);

        String sql = "INSERT INTO " + TABLE_NAME + " (" + NAME_ATTRIBUTE + "," + STATE_ATTRIBUTE
                + ") VALUES (:name, :stateId)";

        return jdbc.update(sql, params) == 1;
    }

    @Transactional
    public int[] create(List<City> cities) {
        SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(cities.toArray());

        String sql = "INSERT INTO " + TABLE_NAME + " (" + NAME_ATTRIBUTE + "," + STATE_ATTRIBUTE
                + ") VALUES (:name, :stateId)";

        return jdbc.batchUpdate(sql, params);
    }

    public boolean delete(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource(ID_ATTRIBUTE, id);

        String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + ID_ATTRIBUTE + "=:id";

        return jdbc.update(sql, params) == 1;
    }


    public City getCity(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource(ID_ATTRIBUTE, id);

        String sql = "SELECT " + ID_ATTRIBUTE + "," + NAME_ATTRIBUTE + "," + STATE_ATTRIBUTE
                + " FROM " + TABLE_NAME+ " WHERE " + ID_ATTRIBUTE + "=:id";

        return jdbc.queryForObject(sql, params, BeanPropertyRowMapper.newInstance(City.class));
    }


    public void deleteStates() {
        String sql = "DELETE FROM " + TABLE_NAME;

        jdbc.getJdbcOperations().execute(sql);
    }

}
