package org.example.services;

import org.example.models.Rental;
import org.example.repositories.CarRepository;
import org.example.repositories.UserRepository;


public class RentalService {
    CarRepository carRepository;
    UserRepository userRepository;

    public RentalService(UserRepository userRepository, CarRepository carRepository) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }

    public Rental saveRental(Long userId, Long carId) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new NullPointerException("user not found");
        }
        if (carRepository.findById(carId).isEmpty()) {
            throw new NullPointerException("car not found");
        }
        return new Rental();
    }

}
