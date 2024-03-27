package org.example.repositories;

import org.example.models.Rental;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RentalRepository extends CrudRepository<Rental, Long> {
    Optional<Rental> findRentalByCarId(Long carId);

}
