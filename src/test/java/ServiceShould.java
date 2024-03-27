import org.example.exceptions.RentalException;
import org.example.models.Car;
import org.example.models.Rental;
import org.example.models.User;
import org.example.repositories.CarRepository;
import org.example.repositories.RentalRepository;
import org.example.repositories.UserRepository;
import org.example.services.RentalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class ServiceShould {
    @Mock
    RentalRepository rentalRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    CarRepository carRepository;
    @InjectMocks
    RentalService rentalService;

    @BeforeEach
    void initService() {
        openMocks(this);
    }

    @Test
    @DisplayName("not save rental, user not found")
    void not_save_rental_user_not_found() {
        Car car = new Car(2L, "4356ORJ", "Mazda");
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        when(carRepository.findById(2L)).thenReturn(Optional.of(car));
        RentalException exception = assertThrows(RentalException.class, () -> rentalService.saveRental(1L, 2L));
        assertEquals("user not found", exception.getMessage());
    }

    @Test
    @DisplayName("not save rental, car not found")
    void not_save_rental_car_not_found() {
        User user = new User(1L, "Mary");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(carRepository.findById(1L)).thenReturn(Optional.empty());
        RentalException exception = assertThrows(RentalException.class, () -> rentalService.saveRental(1L, 2L));
        assertEquals("car not found", exception.getMessage());
    }

    @Test
    @DisplayName("not save rental, car already rented")
    void not_save_rental_rented_car() {
        Car car = new Car(2L, "4356ORJ", "Mazda");
        User user = new User(1L, "Mary");
        Rental rental = new Rental(user, car);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(carRepository.findById(2L)).thenReturn(Optional.of(car));
        when(rentalRepository.findRentalByCarId(2L)).thenReturn(Optional.of(rental));
        RentalException exception = assertThrows(RentalException.class, () -> rentalService.saveRental(1L, 2L));

        assertEquals("car already rented", exception.getMessage());
    }

    @Test
    @DisplayName("save rental")
    void save_rental() throws RentalException {
        Car car = new Car(2L, "4356ORJ", "Mazda");
        User user = new User(1L, "Mary");
        Rental rental = new Rental(user, car);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(carRepository.findById(2L)).thenReturn(Optional.of(car));
        when(rentalRepository.findRentalByCarId(2L)).thenReturn(Optional.empty());

        assertEquals(rental.getCar(), rentalService.saveRental(1l, 2L).getCar());
        assertEquals(rental.getUser(), rentalService.saveRental(1L, 2L).getUser());
    }
}
