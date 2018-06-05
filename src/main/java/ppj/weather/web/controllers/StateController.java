package ppj.weather.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ppj.weather.model.State;
import ppj.weather.servicies.StateService;
import ppj.weather.web.ServerApi;

import java.util.List;

@RestController
public class StateController {

    private StateService stateService;

    @Autowired
    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @RequestMapping(value = ServerApi.STATES_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<State>> getStates() {
        List<State> states = stateService.getAllStates();

        return new ResponseEntity<>(states, HttpStatus.FOUND);
    }
}
