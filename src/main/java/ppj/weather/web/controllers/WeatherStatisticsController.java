package ppj.weather.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ppj.weather.extensions.WeatherStatisticsIntervalExtensions;
import ppj.weather.model.joins.CityWithLatestWeatherRecord;
import ppj.weather.model.joins.CityWithWeatherAverages;
import ppj.weather.model.joins.WeatherStatisticsInterval;
import ppj.weather.servicies.WeatherRecordService;
import ppj.weather.web.ServerApi;

@RestController
public class WeatherStatisticsController {

    private WeatherRecordService weatherRecordService;

    @Autowired
    public WeatherStatisticsController(WeatherRecordService weatherRecordService) {
        this.weatherRecordService = weatherRecordService;
    }

    @RequestMapping(value = ServerApi.LATEST_CITY_WEATHER_PATH, method = RequestMethod.GET)
    public ResponseEntity<CityWithLatestWeatherRecord> getLatestWeatherForCity(@PathVariable("id_city") int idCity) {
        CityWithLatestWeatherRecord record = weatherRecordService.getLatestWeatherRecordForCity(idCity);

        if(record == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(record, HttpStatus.OK);
        }
    }

    @RequestMapping(value = ServerApi.AVERAGE_CITY_WEATHER_PATH, method = RequestMethod.GET)
    public ResponseEntity<CityWithWeatherAverages> getCityLatestAverages(@PathVariable("id_city") int idCity,
        @RequestParam(value = "interval", required = false) String _interval) {

        WeatherStatisticsInterval interval =
                WeatherStatisticsIntervalExtensions.getWeatherStatisticsIntervalByInput(_interval);

        CityWithWeatherAverages result = weatherRecordService.getAverageWeatherForCity(idCity, interval);

        if(result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
}
