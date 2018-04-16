package ppj.weather.repositories;

import org.springframework.data.repository.CrudRepository;
import ppj.weather.model.City;

public interface CityRepository extends CrudRepository<City, Integer> {

}
