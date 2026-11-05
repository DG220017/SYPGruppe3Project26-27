package pocket.moneychecker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pocket.controller.AccountController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import pocket.controller.TransactionController;
import pocket.entity.Account;
import pocket.entity.Transaction;
import pocket.repository.AccountRepository;
import pocket.repository.TransactionRepository;
import pocket.service.AccountService;
import pocket.service.TransactionService;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void createAccount(){
        account1 = new Account(1, "ac1", 12.4);
        account2 = new Account(2,"ac2", 67.4);
        account3 = new Account(3, "ac3", 4.5);
    }

    @Test
    public void testAccountControllerConstructor() {
        // Prüft, ob der Service korrekt im Controller sitzt
        assertEquals(service, controller.getAccountService());
    }


    @Test
    void getAllAccounts(){

    }
}