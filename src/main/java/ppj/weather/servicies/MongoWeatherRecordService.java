package ppj.weather.servicies;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import ppj.weather.model.WeatherRecord;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class MongoWeatherRecordService implements WeatherRecordService {

    private final MongoOperations mongo;

    public MongoWeatherRecordService(MongoOperations mongo) {
        this.mongo = mongo;
    }

    @Override
    public WeatherRecord find(ObjectId objectId) {
        return mongo.findOne(Query.query(where("_Id").is(objectId)), WeatherRecord.class);
    }

    @Override
    public WeatherRecord add(WeatherRecord weatherRecord) {
        mongo.insert(weatherRecord);

        return weatherRecord;
    }

    @Override
    public void remove(WeatherRecord weatherRecord) {
        mongo.remove(weatherRecord);
    }

}
