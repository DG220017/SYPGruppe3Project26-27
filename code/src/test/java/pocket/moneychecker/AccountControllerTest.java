package pocket.moneychecker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pocket.DTO.AccountResponse;
import pocket.controller.AccountController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import pocket.controller.TransactionController;
import pocket.entity.Account;
import pocket.entity.PocketUser;
import pocket.entity.Transaction;
import pocket.repository.AccountRepository;
import pocket.repository.TransactionRepository;
import pocket.service.AccountService;
import pocket.service.TransactionService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class) // WICHTIG: Aktiviert Mockito für diese Klasse
public class AccountControllerTest {

    @Mock
    private AccountRepository repository;

    @Mock
    private AccountService service;

    @InjectMocks
    private AccountController controller; // Wird von Mockito initialisiert und erhält die Mocks injiziert

    Account account1;

    Account account2;

    Account account3;

    @BeforeEach
    public void createAccount() {
        PocketUser user1 = new PocketUser("password123", "test@test.com");
        PocketUser user2 = new PocketUser("password456", "test2@test.com");
        PocketUser user3 = new PocketUser("password789", "test§@test.com");

        // Erstelle den Account und übergib/setze den User
        account1 = new Account("TestAccount", 100.0);
        account1.setPocketUser(user1);

        account2 = new Account( "TestAccount2", 12.4);
        account2.setPocketUser(user2);

        account3 = new Account( "TestAccount3", 4000);
        account3.setPocketUser(user3);


    }

    @Test
    public void testAccountControllerConstructor() {
        // Prüft, ob der Service korrekt im Controller sitzt
        assertEquals(service, controller.getAccountService());
    }

    @Test
    void getAllAccounts() {

    }

    @Test
    public void testAddAccount() {

        // 1. Erstelle einen gültigen User (falls dein Account einen PocketUser benötigt)
        PocketUser user = new PocketUser();
        user.setId(1L); // Wichtig, damit getId() nicht null ist!

        // 2. Setze den User in deinen Account (oder übergib ihn im Konstruktor)
        account1.setPocketUser(user); // Falls es diesen Setter gibt, ansonsten im Konstruktor übergeben

        // 3. Mocking für den Service
        when(accountService.addAccount(account1)).thenReturn(account1);

        // 4. Act
        ResponseEntity<AccountResponse> response = controller.addAccount(account1);

        // 5. Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        // Evtl. weitere Assertions je nach Aufbau von AccountResponse


    }
}