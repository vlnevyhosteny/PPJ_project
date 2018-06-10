package ppj.weather.servicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import ppj.weather.extensions.WeatherStatisticsIntervalExtensions;
import ppj.weather.model.City;
import ppj.weather.model.WeatherRecord;
import ppj.weather.model.joins.CityWithLatestWeatherRecord;
import ppj.weather.model.joins.CityWithWeatherAverages;
import ppj.weather.model.joins.WeatherStatisticsInterval;
import ppj.weather.repositories.CityRepository;
import ppj.weather.repositories.WeatherRecordRepository;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.Date;
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

    public CityWithLatestWeatherRecord getLatestWeatherRecordForCity(int idCity) {
        Optional<City> city = cityRepository.findById(idCity);
        if(city.isPresent() == false) {
            return null;
        }

        WeatherRecord latest = this.findLatestWeatherRecordForCity(idCity);
        if(latest == null) {
            return null;
        }

        return new CityWithLatestWeatherRecord(city.get(), latest);
    }

    public CityWithWeatherAverages getAverageWeatherForCity(int idCity, WeatherStatisticsInterval interval) {
        if(cityRepository.existsById(idCity) == false) {
            return null;
        }

        if(repository.findFirstByCityIdOrderByIdDesc(idCity) == null) {
            return null;
        }

        Date dateRange = WeatherStatisticsIntervalExtensions.getDateRangeFromNowByInterval(interval);

        GroupOperation groupOperation = Aggregation.group()
                .avg(WeatherRecord.HUMIDITY_NAME).as(CityWithWeatherAverages.HUMIDITY_NAME)
                .avg(WeatherRecord.PRECIPITATION_NAME).as(CityWithWeatherAverages.PRECIPITATION_NAME)
                .avg(WeatherRecord.TEMPERATURE_NAME).as(CityWithWeatherAverages.TEMPERATURE_NAME);

        MatchOperation matchOperation = Aggregation.match(new Criteria(WeatherRecord.DATE_NAME)
                                                                .gt(dateRange)
                                                                .and(WeatherRecord.CITY_ID_NAME)
                                                                .is(idCity));

        ProjectionOperation projectionOperation = Aggregation.project(CityWithWeatherAverages.HUMIDITY_NAME,
                                                                      CityWithWeatherAverages.PRECIPITATION_NAME,
                                                                      CityWithWeatherAverages.TEMPERATURE_NAME);

        projectionOperation = projectionOperation.andExclude(WeatherRecord.ID_NAME);

        Aggregation aggregation = Aggregation.newAggregation(matchOperation, groupOperation, projectionOperation);

        AggregationResults<CityWithWeatherAverages> result = mongoTemplate.aggregate(aggregation,
                                                                                     WeatherRecord.COLLECTION_NAME,
                                                                                     CityWithWeatherAverages.class);

        return result.getUniqueMappedResult();
    }
}
