package web.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ppj.weather.Main;
import ppj.weather.model.City;
import ppj.weather.model.State;
import ppj.weather.model.WeatherRecord;
import ppj.weather.model.joins.CityWithLatestWeatherRecord;
import ppj.weather.model.joins.CityWithWeatherAverages;
import ppj.weather.model.joins.WeatherStatisticsInterval;
import ppj.weather.servicies.CityService;
import ppj.weather.servicies.StateService;
import ppj.weather.servicies.WeatherRecordService;
import ppj.weather.web.ServerApi;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Main.class},
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles({"test"})
public class WeatherStatisticsControllerTest {

    private final String TEST_URL = "http://localhost:8080";

    private Retrofit retrofit = new Retrofit.Builder().baseUrl(TEST_URL)
            .addConverterFactory(JacksonConverterFactory.create()).build();

    private ServerApi restService = retrofit.create(ServerApi.class);

    @Autowired
    private WeatherRecordService weatherRecordService;

    @Autowired
    private StateService stateService;

    @Autowired
    private CityService cityService;

    private State state1 = new State("Czech Republic");
    private State state2 = new State("USA");
    private State state3 = new State("RSA");

    private City city1   = new City("Liberec", state1);
    private City city2   = new City("Praha", state1);
    private City city3   = new City("New York", state2);
    private City city4   = new City("Cape Town", state3);

    @Test
    public void testGetCityWithLatestWeather() throws IOException {
        State state = new State("Czech Republic");
        stateService.create(state);

        City city2 = cityService.create(new City("Liberec", state));

        WeatherRecord record    = new WeatherRecord(city2.getId(), 20, 20, 20);
        weatherRecordService.create(record);
        WeatherRecord record2   = new WeatherRecord(city2.getId(), 30, 30, 30);
        weatherRecordService.create(record2);

        Response<CityWithLatestWeatherRecord> cityWithLatestWeatherRecord
                = restService.getLatestWeatherForCity(city2.getId()).execute();

        assertNotNull(cityWithLatestWeatherRecord);

        double temp = cityWithLatestWeatherRecord.body().getTemperature();
        assertEquals("Temp should be same", (int)record2.getTemperature(),
                (int)temp);
    }

    @Test
    public void testGetAverageWeatherForCity() throws IOException {
        State state = new State("Czech Republic");
        stateService.create(state);

        City city2 = cityService.create(new City("Liberec", state));

        WeatherRecord record    = new WeatherRecord(city2.getId(), 20, 20, 20);
        weatherRecordService.create(record);
        WeatherRecord record2   = new WeatherRecord(city2.getId(), 30, 30, 30);
        weatherRecordService.create(record2);

        Response<CityWithWeatherAverages> cityWithWeatherAverages
                = restService.getAverageWeatherStatisticsInInterval(city2.getId(), "week").execute();

        assertNotNull(cityWithWeatherAverages);
        assertEquals("Temp should be same", 25, (int)cityWithWeatherAverages.body().getTemperature());

        Response<CityWithWeatherAverages> cityWithWeatherAverages1
                = restService.getAverageWeatherStatisticsDefault(city2.getId()).execute();

        assertNotNull(cityWithWeatherAverages1);
        assertEquals("Temp should be same", 25, (int)cityWithWeatherAverages1.body().getTemperature());
    }

}
