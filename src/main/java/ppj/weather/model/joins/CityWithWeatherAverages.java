package ppj.weather.model.joins;

import ppj.weather.model.WeatherRecord;

import java.util.Date;

public class CityWithWeatherAverages extends WeatherRecord {

    private WeatherStatisticsInterval weatherStatisticsInterval = WeatherStatisticsInterval.DAY;

    public CityWithWeatherAverages() {
    }

    public CityWithWeatherAverages(int cityId, double temperature, double humidity, double precipitation) {
        super(cityId, temperature, humidity, precipitation);
    }

    @Override
    @Deprecated
    public void setDate(Date date) {
        super.setDate(date);
    }

    @Override
    @Deprecated
    public String getId() {
        return super.getId();
    }

    @Override
    @Deprecated
    public void setId(String id) {
        super.setId(id);
    }

    @Override
    @Deprecated
    public Date getDate() {
        return super.getDate();
    }

    public WeatherStatisticsInterval getWeatherStatisticsInterval() {
        return weatherStatisticsInterval;
    }

    public void setWeatherStatisticsInterval(WeatherStatisticsInterval weatherStatisticsInterval) {
        this.weatherStatisticsInterval = weatherStatisticsInterval;
    }

    @Override
    public String toString() {
        return "WeatherRecord{" +
                ", cityId=" + super.getCityId() +
                ", temperature=" + super.getTemperature() +
                ", humidity=" + super.getHumidity() +
                ", precipitation=" + super.getPrecipitation() +
                '}';
    }

}
