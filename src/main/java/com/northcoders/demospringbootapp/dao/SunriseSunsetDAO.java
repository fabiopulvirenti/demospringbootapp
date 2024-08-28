package com.northcoders.demospringbootapp.dao;

import com.northcoders.demospringbootapp.exception.ApiConnectionException;
import com.northcoders.demospringbootapp.model.Coordinate;
import com.northcoders.demospringbootapp.model.SunriseSunsetInfo;
import com.northcoders.demospringbootapp.model.SunriseSunsetResponseBody;
import org.springframework.http.ResponseEntity;

public class SunriseSunsetDAO extends DAO {

    public SunriseSunsetDAO() {
        super("https://api.sunrisesunset.io");
    }

    public SunriseSunsetInfo getSunriseSunsetInfo(Coordinate coordinate) {
        ResponseEntity<SunriseSunsetResponseBody> response = this.getWebClient().get()
                .uri((uriBuilder) -> uriBuilder
                        .path("/json")
                        .queryParam("lat", coordinate.latitude())
                        .queryParam("lng", coordinate.longitude())
                        .build()
                )
                .retrieve()
                .toEntity(SunriseSunsetResponseBody.class)
                .block();

        if (response == null || !response.hasBody()) {
            throw new ApiConnectionException("Sunrise Sunset");
        }

        if (response.getBody().results() == null) {
            throw new IllegalArgumentException("Location not found on given coordinate!");
        }

        SunriseSunsetInfo sunriseSunsetInfo = response.getBody().results();

        return sunriseSunsetInfo;
    }
}
