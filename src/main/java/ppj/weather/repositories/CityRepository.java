package ppj.weather.repositories;

import org.springframework.data.repository.CrudRepository;
import ppj.weather.model.City;

import java.util.List;

public interface CityRepository extends CrudRepository<City, Integer> {

    List<City> findAllById_State(int idState);

}
