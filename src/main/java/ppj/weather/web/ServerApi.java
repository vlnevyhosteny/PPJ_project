package ppj.weather.web;

import ppj.weather.model.State;

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

}
