package org.example.models;

public class Rental {
    Long id;
    User user;
    Car car;

    public User getUser() {
        return user;
    }

    public Car getCar() {
        return car;
    }

    public Rental(User user, Car car) {
        this.user = user;
        this.car = car;
    }

}
