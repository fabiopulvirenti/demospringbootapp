package com.northcoders.demospringbootapp.model;

public class Person {

    private String name;

    private int age;

    private String email;

    private String placeOfBirth;

    private String favouriteFood;

    public Person(String name, String email, int age, String placeOfBirth, String favouriteFood) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.placeOfBirth = placeOfBirth;
        this.favouriteFood = favouriteFood;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public String getFavouriteFood() {
        return favouriteFood;
    }
}
