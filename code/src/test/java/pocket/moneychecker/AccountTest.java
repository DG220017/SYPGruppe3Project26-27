package pocket.moneychecker;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import pocket.entity.Account;
import pocket.repository.AccountRepository;

@DataJpaTest
public class AccountTest {

    @Autowired
    private AccountRepository accountRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private Account account;
    private

    @BeforeEach
    void setup(){
        account = new Account("Name", 231698132);
    }

    @Test
    void hasRightToString(){
        Account saved = accountRepository.saveAndFlush(account);
        entityManager.clear();
        Account reloaded = accountRepository.findById(saved.getId()).orElseThrow();

        String expectedUser = "PocketUser{" +
                "id=" + getId() +
                ", password='" + getPassword() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';

        String expected = "Account{" +
                "id=" + saved.getId() +
                ", username='" + "Name" + '\'' +
                ", balance=" + 231698132 +
                ", pocketUser=" + expectedUser +
                '}';

        Assertions.assertEquals(expected, reloaded.toString());
    }




}
