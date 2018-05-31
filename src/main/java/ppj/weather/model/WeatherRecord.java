package ppj.weather.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;

@Document(collection = "weatherRecords")
public class WeatherRecord {

    @Id
    @GeneratedValue
    private String id;

    private City city;

    private int cityId;

    private Date date;


    private double temperature;

    private double humidity;

    private double precipitation;

    public WeatherRecord(String id, int cityId, Date date,
                         double temperature, double humidity, double precipitation) {
        this.id = id;
        this.cityId = cityId;
        this.date = date;
        this.temperature = temperature;
        this.humidity = humidity;
        this.precipitation = precipitation;
    }

    public WeatherRecord(int cityId, double temperature, double humidity,
                         double precipitation) {
        this.cityId = cityId;
        this.date = new Date();
        this.temperature = temperature;
        this.humidity = humidity;
        this.precipitation = precipitation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    @Override
    public String toString() {
        return "WeatherRecord{" +
                "id=" + id +
                ", cityId=" + cityId +
                ", date=" + date +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", precipitation=" + precipitation +
                '}';
    }
}
