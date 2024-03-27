package org.example.services;

import org.example.exceptions.RentalException;
import org.example.models.Rental;
import org.example.repositories.CarRepository;
import org.example.repositories.RentalRepository;
import org.example.repositories.UserRepository;


public class RentalService {
    CarRepository carRepository;
    UserRepository userRepository;
    RentalRepository rentalRepository;

    public RentalService(UserRepository userRepository, CarRepository carRepository, RentalRepository rentalRepository) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.rentalRepository= rentalRepository;
    }

    public Rental saveRental(Long userId, Long carId) throws RentalException {
        if (userRepository.findById(userId).isEmpty()) {
            throw new RentalException("user not found");
        }
        if (carRepository.findById(carId).isEmpty()) {
            throw new RentalException("car not found");
        }
        if(rentalRepository.findRentalByCarId(carId).isPresent()){
            throw new RentalException("car already rented");
        }
        return new Rental(null,null, null);
    }

}
