package servicies;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ppj.weather.Main;
import ppj.weather.model.City;
import ppj.weather.model.State;
import ppj.weather.servicies.CityService;
import ppj.weather.servicies.DownloadWeatherService;
import ppj.weather.servicies.StateService;

import java.io.IOException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Main.class)
@ActiveProfiles({"test"})
public class DownloadWeatherServiceTest {

    @Autowired
    DownloadWeatherService downloadWeatherService;

    @Autowired
    private CityService cityService;

    @Autowired
    private StateService stateService;

    @Test
    public void testDownloadRawWeatherForCity() throws IOException {
        State state = new State("Czech Republic");
        stateService.create(state);

        City city1 = cityService.create(new City("Praha", state));
        City city2 = cityService.create(new City("Liberec", state));


        List<City> cities = cityService.getAll();

        String rawResult = downloadWeatherService.downloadRawWeatherForCity(cities.get(0));


    }

}
