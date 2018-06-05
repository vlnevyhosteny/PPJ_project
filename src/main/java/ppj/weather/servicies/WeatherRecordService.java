package ppj.weather.servicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import ppj.weather.model.City;
import ppj.weather.model.WeatherRecord;
import ppj.weather.model.joins.CityWithLatestWeatherRecord;
import ppj.weather.repositories.CityRepository;
import ppj.weather.repositories.WeatherRecordRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherRecordService {

    private final CityRepository cityRepository;

    private final WeatherRecordRepository repository;

    private final MongoTemplate mongoTemplate;

    @Autowired
    public WeatherRecordService(CityRepository cityRepository, WeatherRecordRepository repository,
                                MongoTemplate mongoTemplate) {

        this.cityRepository = cityRepository;
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    public long getCount() {
        return repository.count();
    }

    public WeatherRecord insert(WeatherRecord weatherRecord) {
        return repository.save(weatherRecord);
    }

    public List<WeatherRecord> insert(List<WeatherRecord> weatherRecords) {
        return repository.saveAll(weatherRecords);
    }

    public List<WeatherRecord> getAll() {
        return repository.findAll();
    }

    public void delete(WeatherRecord weatherRecord) {
        repository.delete(weatherRecord);
    }

    public Optional<WeatherRecord> get(String id) {
        return repository.findById(id) ;
    }

    public WeatherRecord update(WeatherRecord weatherRecord) {
        return repository.save(weatherRecord);
    }

    public List<WeatherRecord> getAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    public List<CityWithLatestWeatherRecord> getCitiesWithLatestWeatherForState(int idState) {
        List<City> cities = cityRepository.findAllById_State(idState);

        List<CityWithLatestWeatherRecord> result = new ArrayList<>();
        cities.forEach((city -> {
            WeatherRecord latest = this.findLatestWeatherRecordForCity(city.getId());

            result.add(new CityWithLatestWeatherRecord(city, latest));
        }));

        return result;
    }

    private WeatherRecord findLatestWeatherRecordForCity(int id) {
        return repository.findFirstByCityOrderByIdDesc(id);
    }
}
