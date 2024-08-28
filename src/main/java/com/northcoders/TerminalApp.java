package com.northcoders;

import com.northcoders.demospringbootapp.dao.MeteoGeocodingDAO;
import com.northcoders.demospringbootapp.dao.SunriseSunsetDAO;
import com.northcoders.demospringbootapp.model.Coordinate;
import com.northcoders.demospringbootapp.model.Location;
import com.northcoders.demospringbootapp.model.SunriseSunsetInfo;

import java.util.Scanner;

public class TerminalApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isStopped = false;

        while (!isStopped) {
            System.out.println("Enter city (Q to quit): ");

            try {
                String cityName = scanner.nextLine();

                if (cityName.trim().equals("Q")) {
                    isStopped = true;
                    break;
                }

                MeteoGeocodingDAO meteoGeocodingDAO = new MeteoGeocodingDAO();
                Location locationOfCity = meteoGeocodingDAO.getCoordinates(cityName);

                SunriseSunsetDAO sunriseSunsetDAO = new SunriseSunsetDAO();
                Coordinate coordinateOfLocation = new Coordinate(locationOfCity.latitude(), locationOfCity.longitude());
                SunriseSunsetInfo sunriseSunsetInfoOfCity = sunriseSunsetDAO.getSunriseSunsetInfo(coordinateOfLocation);

                System.out.println(sunriseSunsetInfoOfCity.toString());
            } catch (RuntimeException e) {
                System.err.println(e.getMessage());
            }
        }

        System.out.println("Bye...");


    }
}
