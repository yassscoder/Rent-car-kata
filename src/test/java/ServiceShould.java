import org.example.repositories.UserRepository;
import org.example.services.RentalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.MockitoAnnotations.openMocks;

public class ServiceShould {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    RentalService rentalService;

    @BeforeEach
    void initService(){
        openMocks(this);
    }

    @Test
    @DisplayName("not save rental, user not found")
    void not_save_rental_user_not_found(){
        RuntimeException exception = assertThrows(RuntimeException.class, () -> rentalService.saveRental(1L, 2L));
        assertEquals("user not found", exception.getMessage());
    }


}
