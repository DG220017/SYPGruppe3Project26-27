package pocket.moneychecker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pocket.DTO.PocketUserResponse;
import pocket.controller.UserController;
import pocket.entity.PocketUser;
import pocket.entity.Transaction;
import pocket.repository.UserRepository;
import pocket.service.UserService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
        user1 = new PocketUser("123","user1@email");
        user2 = new PocketUser("456", "user2@email");
        user3 = new PocketUser("789","user3@email");



    }

    @Test
    public void testAccountControllerConstructor() {
        // Prüft, ob der Service korrekt im Controller sitzt
        assertEquals(service, controller.getUserService());
    }

    @Test
    public void testGetAllUser(){

        List<PocketUserResponse> expectedList = List.of(PocketUserResponse.fromPocketUser(user1),PocketUserResponse.fromPocketUser(user2),PocketUserResponse.fromPocketUser(user3));

        when(service.getAllUsers()).thenReturn(expectedList);

        ResponseEntity<List<PocketUserResponse>> response = controller.getAllUser();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedList, response.getBody());

    }

    @Test
    public void testAddUser(){
        // Arrange
        when(service.addUser(user1))
                .thenReturn(user1);

        // Act
        ResponseEntity<PocketUserResponse> response =
                controller.addUser(user1);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user1, response.getBody());

    }

    @Test
    public void testRemoveUser(){
    }

    @Test
    public void testGetUserById(){

    }

    @Test
    public void testGetUserByEmail(){

    }
}
