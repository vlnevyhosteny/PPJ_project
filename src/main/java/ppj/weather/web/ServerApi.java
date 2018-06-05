package ppj.weather.web;

import ppj.weather.model.State;

import retrofit2.http.*;

import java.util.List;

public interface ServerApi {

    public static final String STATES_PATH = "/states";
    public static final String STATE_PATH  = STATES_PATH + "/{id}";

    @GET(STATES_PATH)
    List<State> showStates();

    @POST(STATES_PATH)
    void addState(@Body State state);

    @GET(STATE_PATH)
    State getState(@Path("id") int id);

    @DELETE(STATE_PATH)
    void deleteState(@Path("id") int id);

}
