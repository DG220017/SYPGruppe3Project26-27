package pocket.moneychecker;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // WICHTIG: Aktiviert Mockito für diese Klasse
public class UserControllerTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserService service;

    @Mock
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

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
        PocketUserResponse user1P = PocketUserResponse.fromPocketUser(user1);

        // Arrange
        when(service.addUser(user1))
                .thenReturn(user1);

        // Act
        ResponseEntity<PocketUserResponse> response =
                controller.addUser(user1);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user1P, response.getBody());

    }

    @Test
    public void testRemoveUser(){


        when(service.removeUser(1L))
                .thenReturn(PocketUserResponse.fromPocketUser(user1));

        // Act
        ResponseEntity<Void> response =
                controller.removeUser(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());

        verify(service).removeUser(1L);
    }

    @Test
    public void testGetUserById(){
        //Arrange
        PocketUserResponse pocketUser = PocketUserResponse.fromPocketUser(user1);


        when(service.getUserById(1L)).thenReturn(pocketUser);

        //Act
        ResponseEntity<PocketUserResponse> response =
                controller.getUserById(1L);

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pocketUser, response.getBody());
    }

    @Test
    public void testGetUserByEmail(){
        //Arrange
        PocketUserResponse pocketUser = PocketUserResponse.fromPocketUser(user1);


        when(service.getUserByEmail("user1@email")).thenReturn(user1);

        //Act
        ResponseEntity<PocketUserResponse> response =
                controller.getUserByEmail("user1@email");

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pocketUser, response.getBody());
    }
}
