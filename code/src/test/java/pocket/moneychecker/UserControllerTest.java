package pocket.moneychecker;

import org.h2.engine.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pocket.controller.AccountController;
import pocket.controller.UserController;
import pocket.entity.Account;
import pocket.entity.PocketUser;
import pocket.repository.AccountRepository;
import pocket.repository.UserRepository;
import pocket.service.AccountService;
import pocket.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class) // WICHTIG: Aktiviert Mockito für diese Klasse
public class UserControllerTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserService service;

    @InjectMocks
    private UserController controller; // Wird von Mockito initialisiert und erhält die Mocks injiziert

    PocketUser user1;
    PocketUser user2;
    PocketUser user3;


    @BeforeEach
    public void createUser(){
        user1 = new PocketUser(1,"123","user1@email");
        user2 = new PocketUser(2, "456", "user2@email");
        user3 = new PocketUser(3,"789","user3@email");
    }

    @Test
    public void testAccountControllerConstructor() {
        // Prüft, ob der Service korrekt im Controller sitzt
        assertEquals(service, controller.getUserService());
    }
}
