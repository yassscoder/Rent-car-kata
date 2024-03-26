package org.example.models;

public class Car {
    Long id;
    String number_plate;
    String brand;

    public Car(Long id, String number_plate, String brand) {
        this.id = id;
        this.number_plate = number_plate;
        this.brand = brand;
    }
}
