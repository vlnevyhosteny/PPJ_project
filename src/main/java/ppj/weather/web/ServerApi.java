package ppj.weather.web;

import ppj.weather.model.City;
import ppj.weather.model.State;

import ppj.weather.model.WeatherRecord;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ServerApi {

    public static final String STATES_PATH = "/states";
    public static final String STATE_PATH  = STATES_PATH + "/{id}";

    @GET(STATES_PATH)
    Call<List<State>> showStates();

    @POST(STATES_PATH)
    Call<State> addState(@Body State state);

    @GET(STATE_PATH)
    Call<State> getState(@Path("id") int id);

    @DELETE(STATE_PATH)
    Call<Void> deleteState(@Path("id") int id);

    @PUT(STATE_PATH)
    Call<State> updateState(@Body State state);


    public static final String CITIES_PATH = "/cities";
    public static final String CITY_PATH  = CITIES_PATH + "/{id}";

    @GET(CITIES_PATH)
    Call<List<City>> showCities();

    @POST(CITIES_PATH)
    Call<City> addCity(@Body City state);

    @GET(CITY_PATH)
    Call<City> getCity(@Path("id") int id);

    @DELETE(CITY_PATH)
    Call<Void> deleteCity(@Path("id") int id);

    @PUT(CITY_PATH)
    Call<City> updateCity(@Body City state);


    public static final String WEATHER_PATH = "/weather";

    public static final String WEATHER_RECORDS_PATH = WEATHER_PATH + "/records";
    public static final String WEATHER_RECORD_PATH = WEATHER_PATH + "/record/{id}";

    @GET(WEATHER_RECORDS_PATH)
    Call<List<WeatherRecord>> showWeatherRecords();

    @POST(WEATHER_RECORDS_PATH)
    Call<WeatherRecord> addWeatherRecord(@Body WeatherRecord state);

    @GET(WEATHER_RECORD_PATH)
    Call<WeatherRecord> getWeatherRecord(@Path("id") String id);

    @DELETE(WEATHER_RECORD_PATH)
    Call<Void> deleteWeatherRecord(@Path("id") String id);

    @PUT(WEATHER_RECORD_PATH)
    Call<WeatherRecord> updateWeatherRecord(@Body WeatherRecord state);
}
