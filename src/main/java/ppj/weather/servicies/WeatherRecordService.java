package ppj.weather.servicies;

import org.bson.types.ObjectId;
import ppj.weather.model.WeatherRecord;

import java.util.Map;

public interface WeatherRecordService {

    WeatherRecord find(ObjectId objectId);

    WeatherRecord add(WeatherRecord user);

    void remove(WeatherRecord user);

}
