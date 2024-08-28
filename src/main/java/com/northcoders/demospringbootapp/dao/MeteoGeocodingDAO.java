package com.northcoders.demospringbootapp.dao;

import com.northcoders.demospringbootapp.model.Location;
import com.northcoders.demospringbootapp.model.MeteoGeoResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

public class MeteoGeocodingDAO extends DAO {


    public MeteoGeocodingDAO() {
        super("https://geocoding-api.open-meteo.com");
    }


    public Location getCoordinates(String city) {
        ResponseEntity<MeteoGeoResult> response = this.getWebClient().get()
                .uri((uriBuilder) -> uriBuilder
                        .path("/v1/search")
                        .queryParam("name", city)
                        .queryParam("count", 1)
                        .build()
                )
                .retrieve()
                .toEntity(MeteoGeoResult.class)
                .block();

        if (response == null || !response.hasBody()) {
            throw new RuntimeException("Something went wrong while reaching out to Meteo Geocoding API!");
        }

        MeteoGeoResult meteoGeoResult = response.getBody();

        if (response.getBody().results() == null) {
            throw new IllegalArgumentException("City not found!");
        }

        return meteoGeoResult.results().getFirst();

    }


}
