package ppj.weather.model.joins;

import ppj.weather.model.City;
import ppj.weather.model.WeatherRecord;

public class CityWithLatestWeatherRecord {

    private City city;

    private WeatherRecord weatherRecord;

    public CityWithLatestWeatherRecord(City city, WeatherRecord weatherRecord) {
        this.city = city;
        this.weatherRecord = weatherRecord;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public WeatherRecord getWeatherRecord() {
        return weatherRecord;
    }

    public void setWeatherRecord(WeatherRecord weatherRecord) {
        this.weatherRecord = weatherRecord;
    }

    public String getCityName() {
        return this.city.getName();
    }

    public double getTemperature() {
        return weatherRecord.getTemperature();
    }

    public double getHumidity() {
        return weatherRecord.getHumidity();
    }

    public double getPrecipitation() {
        return weatherRecord.getPrecipitation();
    }
}
