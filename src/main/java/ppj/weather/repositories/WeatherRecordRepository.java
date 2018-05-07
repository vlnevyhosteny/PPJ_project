package ppj.weather.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ppj.weather.model.WeatherRecord;

import java.util.stream.Stream;

public interface WeatherRecordRepository extends MongoRepository<WeatherRecord, ObjectId> {

    Stream<WeatherRecord> streamAll();

}
