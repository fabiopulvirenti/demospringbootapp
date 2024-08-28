package com.northcoders.demospringbootapp.controller;

import com.northcoders.demospringbootapp.dao.MeteoGeocodingDAO;
import com.northcoders.demospringbootapp.model.Location;
import com.northcoders.demospringbootapp.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/location")
    public Location getLocationofCities(){

        MeteoGeocodingDAO meteo = new MeteoGeocodingDAO();
        System.out.println(meteo.getLocation("London"));
        return meteo.getLocation("London");

    }


}
