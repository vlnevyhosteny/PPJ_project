package ppj.weather.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ppj.weather.model.WeatherRecord;

public interface WeatherRecordRepository extends MongoRepository<WeatherRecord, String> {

}
