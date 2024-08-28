package com.northcoders.demospringbootapp.model;

import java.util.List;

public record MeteoGeoResponseBody(List<Location> results) {
}
