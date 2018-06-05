package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ppj.weather.Main;
import ppj.weather.model.City;
import ppj.weather.model.State;
import ppj.weather.model.WeatherRecord;
import ppj.weather.model.joins.CityWithLatestWeatherRecord;
import ppj.weather.servicies.CityService;
import ppj.weather.servicies.StateService;
import ppj.weather.servicies.WeatherRecordService;

import javax.xml.ws.Response;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Main.class)
@ActiveProfiles({"test"})
public class WeatherRecordServiceTest {

    @Autowired
    private WeatherRecordService weatherRecordService;

    @Autowired
    private StateService stateService;

    @Autowired
    private CityService cityService;

    @Test
    public void testCreateWeatherRecord() {
        WeatherRecord record = new WeatherRecord(1, 20, 20, 20);
        long previous = weatherRecordService.getCount();

        WeatherRecord response = weatherRecordService.insert(record);
        long now = weatherRecordService.getCount();

        assertEquals("Record has been inserted", previous, (now - 1));
        assertTrue("Record has been inserted", response.getId() != null);
    }

    @Test
    public void testDeleteWeatherRecord() {
        WeatherRecord record    = new WeatherRecord(1, 20, 20, 20);
        weatherRecordService.insert(record);
        WeatherRecord record2   = new WeatherRecord(1, 20, 20, 20);
        weatherRecordService.insert(record2);

        long previousCount = weatherRecordService.getCount();

        weatherRecordService.delete(record);
        long count = weatherRecordService.getCount();

        assertTrue("Count should be one less", previousCount == (count + 1));

        assertFalse("Removed should not be present", weatherRecordService
                .get(record.getId())
                .isPresent());
    }

    @Test
    public void testUpdateWeatherRecord() {
        WeatherRecord record    = new WeatherRecord(1, 20, 20, 20);
        weatherRecordService.insert(record);
        WeatherRecord record2   = new WeatherRecord(1, 20, 20, 20);
        weatherRecordService.insert(record2);

        WeatherRecord record1   = weatherRecordService.getAll().get(0);
        record1.setTemperature(100);
        weatherRecordService.update(record1);

        assertTrue("Should be present", weatherRecordService.get(record1.getId())
            .isPresent());
        assertTrue("Should be updated", weatherRecordService.get(record1.getId())
            .get().getTemperature() == 100);
    }

    @Test
    public void testGetWeatherRecord() {
        WeatherRecord record    = new WeatherRecord(1, 20, 20, 20);
        weatherRecordService.insert(record);
        WeatherRecord record2   = new WeatherRecord(1, 20, 20, 20);
        weatherRecordService.insert(record2);

        Optional<WeatherRecord> result = weatherRecordService.get(record2.getId());
        assertTrue("Should be present", result.isPresent());
        assertNotNull("Should be not null", result.get());
    }

    @Test
    public void testGetPageableWeatherRecord() {
        WeatherRecord record    = new WeatherRecord(1, 20, 20, 20);
        weatherRecordService.insert(record);
        WeatherRecord record2   = new WeatherRecord(1, 20, 20, 20);
        weatherRecordService.insert(record2);
        WeatherRecord record3    = new WeatherRecord(1, 20, 20, 20);
        weatherRecordService.insert(record3);
        WeatherRecord record4   = new WeatherRecord(1, 20, 20, 20);
        weatherRecordService.insert(record4);

        Iterable<WeatherRecord> result = weatherRecordService.getAll(PageRequest.of(1, 2));
        WeatherRecord[] arrResult = ((List<WeatherRecord>) result).toArray(new WeatherRecord[((List<WeatherRecord>) result).size()]);

        assertTrue("Should be same", arrResult[0].getId().equals(record3.getId()));
        assertTrue("Should be same", arrResult[1].getId().equals(record4.getId()));
    }

    @Test
    public void testGetCitiesWithLatestWeatherForState() {
        State state = new State("Czech Republic");
        stateService.create(state);

        cityService.create(new City("Praha", state));
        cityService.create(new City("Liberec", state));

        WeatherRecord record4   = new WeatherRecord(1, 20, 20, 20);
        weatherRecordService.insert(record4);
        WeatherRecord record    = new WeatherRecord(3, 20, 20, 20);
        weatherRecordService.insert(record);
        WeatherRecord record2   = new WeatherRecord(3, 30, 30, 30);
        weatherRecordService.insert(record2);

        List<CityWithLatestWeatherRecord> cityWithLatestWeatherRecords
            = weatherRecordService.getCitiesWithLatestWeatherForState(1);

        assertTrue("Should be more than zero", cityWithLatestWeatherRecords.size() > 0);
        assertTrue("Should be latest temp", cityWithLatestWeatherRecords.get(1).getTemperature() == record2.getTemperature());
    }

    @Before
    public final void setUp() {
        for (WeatherRecord item:
                weatherRecordService.getAll()) {

            weatherRecordService.delete(item);
        }
    }
}
