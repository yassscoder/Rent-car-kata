package org.example.services;

import org.example.models.Rental;
import org.example.repositories.UserRepository;


public class RentalService {
    UserRepository userRepository;

    public RentalService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Rental saveRental(Long userId, Long carId) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new NullPointerException("user not found");
        }
        return new Rental();
    }

}
