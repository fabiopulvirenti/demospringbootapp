package com.northcoders.demospringbootapp.dao;

import com.northcoders.demospringbootapp.exception.ApiConnectionException;
import com.northcoders.demospringbootapp.model.Location;
import com.northcoders.demospringbootapp.model.MeteoGeoResponseBody;
import org.springframework.http.ResponseEntity;

public class MeteoGeocodingDAO extends DAO {


    public MeteoGeocodingDAO() {
        super("https://geocoding-api.open-meteo.com");
    }


    public Location getCoordinates(String city) {
        ResponseEntity<MeteoGeoResponseBody> response = this.getWebClient().get()
                .uri((uriBuilder) -> uriBuilder
                        .path("/v1/search")
                        .queryParam("name", city)
                        .queryParam("count", 1)
                        .build()
                )
                .retrieve()
                .toEntity(MeteoGeoResponseBody.class)
                .block();

        if (response == null || !response.hasBody()) {
            throw new ApiConnectionException("Meteo Geocoding");
        }

        MeteoGeoResponseBody meteoGeoResult = response.getBody();

        if (response.getBody().results() == null) {
            throw new IllegalArgumentException("City not found!");
        }

        return meteoGeoResult.results().getFirst();

    }


}
