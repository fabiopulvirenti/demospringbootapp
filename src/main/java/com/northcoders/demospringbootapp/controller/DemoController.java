package com.northcoders.demospringbootapp.controller;

import com.northcoders.demospringbootapp.dao.MeteoGeocodingDAO;
import com.northcoders.demospringbootapp.model.Location;
import com.northcoders.demospringbootapp.model.LocationCoordinate;
import com.northcoders.demospringbootapp.model.Person;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class DemoController {


    @GetMapping("/hello")
    public String greeting(){
        return "Hello there";

    }

    @PostMapping("/hello")
    public String greetingPost(){
        return "Hello there Post";
    }

    @GetMapping("/person")
    public List<Person> getPeople(){

        List<Person> people = new ArrayList<>(){{
            add(new Person("John","gmailcom",25,"London","pizza"));
            add(new Person("Mary","gmailcom",30,"London","pasta"));
            add(new Person("Cevdet","gmailcom",35,"London","pasta"));
            add(new Person("Fabio","gmailcom",24,"London","pizza"));
            add(new Person("Simon","gmailcom",35,"Manchester","noodle"));
        }};
        return people;


    }

    @GetMapping("/coordinates")
    public LocationCoordinate getCoordinateOfCity(@RequestParam(name = "city") String cityName){

        MeteoGeocodingDAO meteo = new MeteoGeocodingDAO();
        Location location = meteo.getCoordinates(cityName);
//        System.out.println(location);

        LocationCoordinate locationCoordinate = new LocationCoordinate(location.latitude(), location.longitude());

        return locationCoordinate;
    }


}
