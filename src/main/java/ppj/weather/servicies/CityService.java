package ppj.weather.servicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ppj.weather.model.City;
import ppj.weather.repositories.CityRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> getOffers() {
        return StreamSupport.stream(cityRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void create(City offer) {
        cityRepository.save(offer);
    }

    public City getCity(Integer id) {
        return cityRepository.findOne(id);
    }


    public void saveOrUpdate(City offer) {
        cityRepository.save(offer);
    }

    public void delete(int id) {
        cityRepository.delete(id);
    }

    public void deleteOffers() {
        cityRepository.deleteAll();
    }

}
