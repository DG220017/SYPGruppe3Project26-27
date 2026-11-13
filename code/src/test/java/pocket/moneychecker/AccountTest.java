package pocket.moneychecker;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import pocket.entity.Account;
import pocket.entity.PocketUser;
import pocket.repository.AccountRepository;
import pocket.repository.UserRepository;

@DataJpaTest
public class AccountTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private Account account;
    private PocketUser pocketUser;

    @BeforeEach
    void setup(){
        account = new Account("Name", 231698132);
        pocketUser = new PocketUser("123", "test@gmail.com");
    }

    @Test
    void hasRightToString(){
        Account saved = accountRepository.saveAndFlush(account);
        entityManager.clear();
        Account reloaded = accountRepository.findById(saved.getId()).orElseThrow();

        PocketUser savedUser = userRepository.save(pocketUser);

        String expectedUser = "PocketUser{" +
                "id=" + savedUser.getId() +
                ", password='" + "123" + '\'' +
                ", email='" + "test@gmail.com" + '\'' +
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
