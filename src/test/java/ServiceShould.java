import org.example.models.Car;
import org.example.repositories.CarRepository;
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
        RuntimeException exception = assertThrows(RuntimeException.class, () -> rentalService.saveRental(1L, 2L));
        assertEquals("user not found", exception.getMessage());
    }


}
