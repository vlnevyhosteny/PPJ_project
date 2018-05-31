package model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ppj.weather.Main;
import ppj.weather.model.WeatherRecord;
import ppj.weather.servicies.WeatherRecordService;

import javax.xml.ws.Response;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Main.class)
@ActiveProfiles({"test"})
public class WeatherRecordServiceTest {

   @Autowired
   private WeatherRecordService weatherRecordService;

    @Test
    public void testCreateWeatherRecord() {
        WeatherRecord record = new WeatherRecord(1, 20, 20, 20);
        long previous = weatherRecordService.getCount();

        WeatherRecord response = weatherRecordService.insert(record);
        long now = weatherRecordService.getCount();

        assertEquals("Record has been inserted", previous, (now - 1));
        assertTrue("Record has been inserted", response.getId() != null);
    }

}
