package com.vfl.mutirao_solidario.controller;

import com.vfl.mutirao_solidario.client.CitiesClient;
import com.vfl.mutirao_solidario.client.City;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/city")
@AllArgsConstructor
public class CityController {

    private final CitiesClient citiesClient;

    @RequestMapping(method = RequestMethod.GET)
    public List<City> findCity(@RequestParam String name) throws Exception {
        return citiesClient.findCitiesByName(name);
    }

}
