package com.vfl.mutirao_solidario.client.impl;

import com.vfl.mutirao_solidario.client.CitiesClient;
import com.vfl.mutirao_solidario.client.City;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class Back4App implements CitiesClient {

    @Value("${back4app.url}")
    private String url;

    @Value("${back4app.app-id}")
    private String appId;

    @Value("${back4app.key}")
    private String key;

    @Override
    public List<City> findCitiesByName(String name) throws Exception {

        try {
            String where = URLEncoder.encode("{" +
                    "    \"name\": {" +
                    "        \"$regex\": \"^.*" + name + ".*$\"" +
                    "    }" +
                    "}", StandardCharsets.UTF_8);

            String baseUrl = url;
            URL url = new URL(baseUrl + where);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestProperty("X-Parse-Application-Id", appId);
            urlConnection.setRequestProperty("X-Parse-REST-API-Key", key);
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                JSONObject data = new JSONObject(stringBuilder.toString());

                Iterator<Object> i = ((JSONArray)data.get("results")).iterator();

                ArrayList<City> cities = new ArrayList<>();

                while(i.hasNext()){
                       JSONObject j = (JSONObject) i.next();
                       cities.add(City.builder()
                               .name((String)j.get("name"))
                               .id(String.valueOf(j.get("cityId")))
                               .latitude(((BigDecimal)((JSONObject)j.get("location")).get("latitude")).doubleValue())
                               .longitude(((BigDecimal)((JSONObject)j.get("location")).get("longitude")).doubleValue())
                               .build());
                }

                return cities;

            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            throw new Exception("Internal server error");
        }
    }

}
