package pocket.moneychecker;

import entity.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.AccountRepository;
import service.AccountService;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void createAccounts() {
        when(accountRepository.findAll()).thenReturn(List.of(
                new Account(1, "Name1", 100),
                new Account(2, "Name5", -100),
                new Account(3, "name12", 10000),
                new Account(4, "enam1", 9342),
                new Account(5, "jame21", -9834),
                new Account(6, "0asd12", 21493)
        ));
    }

    @Test
    void testSortedAccounts() {
        Assertions.assertEquals(6, accountService.getAllAccounts().size());
    }
}