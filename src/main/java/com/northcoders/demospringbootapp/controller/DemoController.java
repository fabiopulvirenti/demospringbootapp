package com.northcoders.demospringbootapp.controller;

import com.northcoders.demospringbootapp.dao.MeteoGeocodingDAO;
import com.northcoders.demospringbootapp.dao.SunriseSunsetDAO;
import com.northcoders.demospringbootapp.model.Coordinate;
import com.northcoders.demospringbootapp.model.Location;
import com.northcoders.demospringbootapp.model.Person;
import com.northcoders.demospringbootapp.model.SunriseSunsetInfo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class DemoController {


    @GetMapping("/hello")
    public String greeting() {
        return "Hello there";

    }

    @PostMapping("/hello")
    public String greetingPost() {
        return "Hello there Post";
    }

    @GetMapping("/person")
    public List<Person> getPeople() {

        List<Person> people = new ArrayList<>() {{
            add(new Person("John", "gmailcom", 25, "London", "pizza"));
            add(new Person("Mary", "gmailcom", 30, "London", "pasta"));
            add(new Person("Cevdet", "gmailcom", 35, "London", "pasta"));
            add(new Person("Fabio", "gmailcom", 24, "London", "pizza"));
            add(new Person("Simon", "gmailcom", 35, "Manchester", "noodle"));
        }};
        return people;


    }

    @GetMapping("/coordinates")
    public Coordinate getCoordinateOfCity(@RequestParam(name = "city") String cityName) {

        MeteoGeocodingDAO meteo = new MeteoGeocodingDAO();
        Location location = meteo.getCoordinates(cityName);
//        System.out.println(location);

        Coordinate locationCoordinate = new Coordinate(location.latitude(), location.longitude());

        return locationCoordinate;
    }


    @GetMapping("/sunrise-sunset")
    public SunriseSunsetInfo getSunriseSunsetInfoOfTheLocation(@RequestParam(name = "lat") double latitude, @RequestParam(name = "long") double longitude) {
        if (latitude < 0 || latitude > 180
                || longitude < 0 || longitude > 360
        ) {
            throw new IllegalArgumentException("""
                    Coordinate values must be valid!
                    - latitude: between 0 and 180 
                    - longitude: between 0 and 360 
                    """);
        }

        SunriseSunsetDAO sunriseSunsetDAO = new SunriseSunsetDAO();

        SunriseSunsetInfo sunriseSunsetInfo = sunriseSunsetDAO.getSunriseSunsetInfo(new Coordinate(latitude, longitude));

        return sunriseSunsetInfo;
    }
}
