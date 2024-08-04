package com.vfl.mutirao_solidario.client;

import java.util.List;

public interface CitiesClient {

    List<City> findCitiesByName(String name) throws Exception;

}
