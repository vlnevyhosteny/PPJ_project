package ppj.weather.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ppj.weather.model.State;
import ppj.weather.servicies.StateService;
import ppj.weather.servicies.WeatherRecordService;
import ppj.weather.web.MvcRoutes;

import java.util.List;

@Controller
public class ActualWeatherController {

    private StateService stateService;

    private WeatherRecordService weatherRecordService;

    public ActualWeatherController(StateService stateService, WeatherRecordService weatherRecordService) {
        this.stateService = stateService;
        this.weatherRecordService = weatherRecordService;
    }

    @GetMapping({MvcRoutes.ROOT, MvcRoutes.INDEX, MvcRoutes.ACTUAL_WEATHER})
    public String index(Model model) {
        List<State> states = stateService.getAllStates();
        model.addAttribute("states", states);

        return "index";
    }

}
