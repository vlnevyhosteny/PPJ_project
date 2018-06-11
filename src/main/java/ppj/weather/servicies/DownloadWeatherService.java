package ppj.weather.servicies;

import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import ppj.weather.config.WeatherSourceConfig;
import ppj.weather.model.City;
import ppj.weather.model.WeatherRecord;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
public class DownloadWeatherService {

    private final static String CITY_PAR_NAME = "${CITY_NAME}";

    private final static String API_KEY_PAR_NAME = "${API_KEY}";

    private final static int CONN_TIMEOUT = 1000;

    private final static int READ_TIMEOUT = 1000;

    private StateService stateService;

    private CityService cityService;

    private WeatherRecordService weatherRecordService;

    private WeatherSourceConfig weatherSourceConfig;

    private ThreadPoolTaskScheduler weatherThreadPoolTaskScheduler;

    @Autowired
    public DownloadWeatherService(StateService stateService, CityService cityService,
                                  WeatherRecordService weatherRecordService, WeatherSourceConfig weatherSourceConfig,
                                  ThreadPoolTaskScheduler weatherThreadPoolTaskScheduler) {

        this.stateService = stateService;
        this.cityService = cityService;
        this.weatherRecordService = weatherRecordService;
        this.weatherSourceConfig = weatherSourceConfig;
        this.weatherThreadPoolTaskScheduler = weatherThreadPoolTaskScheduler;

        startWeatherDownloadingScheduler();
    }

    private void startWeatherDownloadingScheduler() {
        weatherThreadPoolTaskScheduler.scheduleAtFixedRate(this::downloadWeatherForCities,
                                                            weatherSourceConfig.getUpdateRate());
    }

    private void downloadWeatherForCities() {
        List<City> cities = cityService.getAll();

        for (City city:
             cities) {
                downloadWeatherForCity(city);
        }
    }

    private void downloadWeatherForCity(City city) {
        try {
            String rawResult = downloadRawWeatherForCity(city);
            WeatherRecord parsedResult = parseRawStringToWeatherRecord(rawResult, city);

            weatherRecordService.save(parsedResult);
        } catch (Exception ex) {
            // TODO: log
        }
    }

    private WeatherRecord parseRawStringToWeatherRecord(String rawResult, City city) {
        WeatherRecord weatherRecord = new WeatherRecord();

        weatherRecord.setCity(city);
        weatherRecord.setCityId(city.getId());
        weatherRecord.setTemperature(JsonPath.read(rawResult, "$.main.temp"));
        weatherRecord.setTemperature(JsonPath.read(rawResult, "$.main.humidity"));
        weatherRecord.setTemperature(JsonPath.read(rawResult, "$.main.pressure"));

        return weatherRecord;
    }

    public String downloadRawWeatherForCity(City city) throws IOException {
        URL url = new URL(composeURLForCity(city));

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(CONN_TIMEOUT);
        connection.setReadTimeout(READ_TIMEOUT);

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line;
        StringBuilder builder = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }

        reader.close();

        return builder.toString();
    }

    private String composeURLForCity(City city) {
        return weatherSourceConfig.getWeatherUrl().replace(CITY_PAR_NAME, String.valueOf(city.getName()))
                                                  .replace(API_KEY_PAR_NAME, weatherSourceConfig.getApiKey());
    }
}
