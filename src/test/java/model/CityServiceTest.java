package model;

import org.junit.Before;
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
import ppj.weather.servicies.StateService;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Main.class)
@ActiveProfiles({"test"})
public class CityServiceTest {

    @Autowired
    private CityService cityService;

    @Autowired
    private StateService stateService;

    private State state1 = new State( "UK");
    private State state2 = new State( "Czech Republic");
    private State state3 = new State( "Germany");
    private State state4 = new State( "Swiss");

    private City city1 = new City("Liberec", state2);
    private City city2 = new City("Praha", state2);
    private City city3 = new City("London", state1);
    private City city4 = new City("Berlin", state3);
    private City city5 = new City("Munchen", state3);
    private City city6 = new City("Nurnberg", state3);
    private City city7 = new City("Zurich", state4);

    @Before
    public void init() {
        cityService.deleteCities();
    }

    @Test
    public void testCreateRetrieve() {
        stateService.create(state1);
        stateService.create(state2);
        stateService.create(state3);
        stateService.create(state4);

        cityService.saveOrUpdate(city1);

        List<City> cities1 = cityService.getCities();
        assertEquals("Should be one city.", 1, cities1.size());

        assertEquals("Retrieved city should equal inserted city", city1.toString(),
                cities1.get(0).toString());

        cityService.saveOrUpdate(city2);
        cityService.saveOrUpdate(city3);
        cityService.saveOrUpdate(city4);
        cityService.saveOrUpdate(city5);
        cityService.saveOrUpdate(city6);
        cityService.saveOrUpdate(city7);

        List<City> cities = cityService.getCities();
        assertEquals("Should be six cities for states.", 7,
                cities.size());
    }

    @Test
    public void testDelete() {
        stateService.create(state1);
        stateService.create(state2);
        stateService.create(state3);
        stateService.create(state4);

        cityService.saveOrUpdate(city1);
        cityService.saveOrUpdate(city2);
        cityService.saveOrUpdate(city3);
        cityService.saveOrUpdate(city4);
        cityService.saveOrUpdate(city5);
        cityService.saveOrUpdate(city6);
        cityService.saveOrUpdate(city7);

        City retrieved1 = cityService.getCity(city1.getId());
        assertTrue("Some is found", retrieved1 != null);

        cityService.delete(city2.getId());
        City retrieved2 = cityService.getCity(city2.getId());
        assertNull("Deleted city is not present", retrieved2);
    }

    @Test
    public void testGetById() {
        stateService.create(state1);
        stateService.create(state2);
        stateService.create(state3);
        stateService.create(state4);

        cityService.saveOrUpdate(city1);
        cityService.saveOrUpdate(city2);
        cityService.saveOrUpdate(city3);
        cityService.saveOrUpdate(city4);
        cityService.saveOrUpdate(city5);
        cityService.saveOrUpdate(city6);

        City retrieved1 = cityService.getCity(city1.getId());
        assertNotNull("Some is found", retrieved1);

        City retrieved2 = cityService.getCity(city7.getId());
        assertNull("City is not present", retrieved2);
    }

    @Test
    public void testUpdate() {
        stateService.create(state1);
        stateService.create(state2);
        stateService.create(state3);
        stateService.create(state4);

        cityService.saveOrUpdate(city1);
        cityService.saveOrUpdate(city2);
        cityService.saveOrUpdate(city3);
        cityService.saveOrUpdate(city4);
        cityService.saveOrUpdate(city5);
        cityService.saveOrUpdate(city6);
        cityService.saveOrUpdate(city7);

        city3.setName("Leeds");
        cityService.saveOrUpdate(city3);

        City retrieved = cityService.getCity(city3.getId());
        assertEquals("Retrieved city should be updated", retrieved.toString(), city3.toString());
    }

}
