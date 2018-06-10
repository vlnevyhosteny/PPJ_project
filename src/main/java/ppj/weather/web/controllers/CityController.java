package ppj.weather.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ppj.weather.model.City;
import ppj.weather.model.State;
import ppj.weather.servicies.CityService;
import ppj.weather.web.ServerApi;

import java.util.List;
import java.util.Optional;

@RestController
public class CityController {

    CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @RequestMapping(value = ServerApi.CITIES_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<City>> getCities() {
        List<City> cities = cityService.get();

        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.CITIES_PATH, method = RequestMethod.POST)
    public ResponseEntity<City> createCity(@RequestBody City city) {
        if(cityService.exists(city.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            cityService.create(city);

            return new ResponseEntity<>(city, HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = ServerApi.CITY_PATH, method = RequestMethod.PUT)
    public ResponseEntity<City> updateCity(@RequestBody City city) {
        Optional<City> result = cityService.get(city.getId());

        if(result.isPresent()) {
            City updated = cityService.save(city);

            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = ServerApi.CITY_PATH, method = RequestMethod.GET)
    public ResponseEntity<City> getCity(@PathVariable("id") int id) {
        Optional<City> result = cityService.get(id);

        if(result.isPresent() == false) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
    }

    @RequestMapping(value = ServerApi.CITY_PATH, method = RequestMethod.DELETE)
    public ResponseEntity deleteCity(@PathVariable("id") int id) {
        Optional<City> result = cityService.get(id);

        if(result.isPresent()) {
            cityService.delete(result.get());

            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
