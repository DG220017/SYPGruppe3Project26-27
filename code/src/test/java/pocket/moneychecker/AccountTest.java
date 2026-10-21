package pocket.moneychecker;

import controller.AccountController;
import entity.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.AccountRepository;
import service.AccountService;

@ExtendWith(MockitoExtension.class)
public class AccountTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountService accountService;

    @InjectMocks
    AccountController accountController;

    @BeforeEach
    void createAccounts() {
        accountService.addAccount(new Account(1, "Name1", 100));
        accountService.addAccount(new Account(2, "Name5", -100));
        accountService.addAccount(new Account(3, "name12", 10000));
        accountService.addAccount(new Account(4, "enam1", 9342));
        accountService.addAccount(new Account(5, "jame21", -9834));
        accountService.addAccount(new Account(6, "0asd12", 21493));
    }

    @Test
    void testSortedAccounts() {
        Assertions.assertEquals(6, accountService.getAllAccounts().size());
    }
}