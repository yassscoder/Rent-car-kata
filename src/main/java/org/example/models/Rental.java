package org.example.models;

public class Rental {
    Long id;
    User user;
    Car car;

    public Rental(Long id, User user, Car car) {
        this.id = id;
        this.user = user;
        this.car = car;
    }
}
