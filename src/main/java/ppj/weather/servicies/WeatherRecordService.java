package ppj.weather.servicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import ppj.weather.model.WeatherRecord;
import ppj.weather.repositories.CityRepository;
import ppj.weather.repositories.WeatherRecordRepository;

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

}
