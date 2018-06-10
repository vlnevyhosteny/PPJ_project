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

    @Autowired
    public WeatherRecordService(CityRepository cityRepository, WeatherRecordRepository repository) {

        this.cityRepository = cityRepository;
        this.repository = repository;
    }

    public long getCount() {
        return repository.count();
    }

    public WeatherRecord create(WeatherRecord weatherRecord) {
        return repository.save(weatherRecord);
    }

    public List<WeatherRecord> create(List<WeatherRecord> weatherRecords) {
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

    public WeatherRecord save(WeatherRecord weatherRecord) {
        return repository.save(weatherRecord);
    }

    public List<WeatherRecord> getAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    public List<CityWithLatestWeatherRecord> getCitiesWithLatestWeatherForState(int idState) {
        List<City> cities = cityRepository.findAllByState_Id(idState);

        List<CityWithLatestWeatherRecord> result = new ArrayList<>();
        cities.forEach((city -> {
            WeatherRecord latest = this.findLatestWeatherRecordForCity(city.getId());

            if(latest != null) {
                result.add(new CityWithLatestWeatherRecord(city, latest));
            }
        }));

        return result;
    }

    private WeatherRecord findLatestWeatherRecordForCity(int id) {
        return repository.findFirstByCityIdOrderByIdDesc(id);
    }

    public boolean exists(String id) {
        return repository.existsById(id);
    }

    public int getWeatherRecordCount() {
        return (int) repository.count();
    }
}
