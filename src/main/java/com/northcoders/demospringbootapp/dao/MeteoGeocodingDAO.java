package com.northcoders.demospringbootapp.dao;

import com.northcoders.demospringbootapp.model.Location;
import com.northcoders.demospringbootapp.model.MeteoGeoResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

public class MeteoGeocodingDAO {


private WebClient webClient;

public MeteoGeocodingDAO() {
    this.webClient = WebClient.builder().baseUrl("https://geocoding-api.open-meteo.com").build();
}


public Location getLocation(String city){
    ResponseEntity<MeteoGeoResult> response = webClient.get()
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
        throw new RuntimeException("Something went wrong while reaching out to MeteoGeocoding API!");
    }

    MeteoGeoResult meteoGeoResult = response.getBody();

    return meteoGeoResult.results().getFirst();

}





}
