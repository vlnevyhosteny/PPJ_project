package ppj.weather.servicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ppj.weather.model.City;
import ppj.weather.repositories.CityRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> getCities() {
        return StreamSupport.stream(cityRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void create(City city) {
        cityRepository.save(city);
    }

    public void saveOrUpdate(City city) {
        cityRepository.save(city);
    }

    public void deleteCities() {
        cityRepository.deleteAll();
    }

    public City getCity(int id) {
        Optional<City> city = cityRepository.findById(id);

        if(city.isPresent()) {
            return city.get();
        } else {
            return null;
        }
    }

    public void delete(int id) {
        cityRepository.deleteById(id);
    }
}
