package ppj.weather.extensions;

import ppj.weather.model.joins.WeatherStatisticsInterval;

import java.util.Calendar;
import java.util.Date;

public class WeatherStatisticsIntervalExtensions {

    public static WeatherStatisticsInterval getWeatherStatisticsIntervalByInput(String input) {
        if(input == null) {
            return WeatherStatisticsInterval.DAY;
        }

        input = input.toUpperCase();

        switch (input) {
            case "WEEK":
                return WeatherStatisticsInterval.WEEK;
            case "TWO_WEEKS":
                return WeatherStatisticsInterval.TWO_WEEKS;

                default:
                    return WeatherStatisticsInterval.DAY;
        }
    }

    public static Date getDateRangeFromNowByInterval(WeatherStatisticsInterval interval) {
        Calendar calendar = Calendar.getInstance();

        switch (interval) {
            case WEEK:
                calendar.add(Calendar.DAY_OF_YEAR, -7);
                break;
            case TWO_WEEKS:
                calendar.add(Calendar.DAY_OF_YEAR, -14);
                break;

                default:
                    calendar.add(Calendar.DAY_OF_YEAR, -1);
                    break;
        }

        return calendar.getTime();
    }

}
