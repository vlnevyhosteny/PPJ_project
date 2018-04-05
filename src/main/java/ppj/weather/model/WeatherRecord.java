package ppj.weather.model;

import java.util.Date;

public class WeatherRecord {

    private int id;

    private City city;

    private int cityId;

    private Date date;

    private Weather weather;

    public WeatherRecord(int id, City city, int cityId, Date date, Weather weather) {
        this.id = id;
        this.city = city;
        this.cityId = cityId;
        this.date = date;
        this.weather = weather;
    }

    public WeatherRecord(int id, City city, int cityId, Date date,
                         double temperature, double humidity, double precipitation) {
        this.id = id;
        this.city = city;
        this.cityId = cityId;
        this.date = date;
        this.weather = new Weather(temperature, humidity, precipitation);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "WeatherRecord{" +
                "id=" + id +
                ", city=" + city +
                ", cityId=" + cityId +
                ", date=" + date +
                ", weather=" + weather +
                '}';
    }
}
