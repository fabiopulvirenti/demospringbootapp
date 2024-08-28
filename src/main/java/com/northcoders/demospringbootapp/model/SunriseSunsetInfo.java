package com.northcoders.demospringbootapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SunriseSunsetInfo(String sunrise,
                                String sunset,
                                @JsonProperty("first_light") String firstLight,
                                @JsonProperty("last_light") String lastLight,
                                String timezone) {

    @Override
    public String toString() {
        return "Sunrise: " + sunrise() + " Sunset: " + sunset();
    }
}
