package pocket.moneychecker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pocket.entity.PocketUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pocket.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserTest {
    PocketUser pocketUser1;
    PocketUser pocketUser2;

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    void setup(){
        pocketUser1 = new PocketUser("dzew","asd@asd.com");
        pocketUser2 = new PocketUser("adfs", "123@gmail.com");
    }

    @Test
    void userEmailTest(){
        assertEquals("asd@asd.com", pocketUser1.getEmail());
    }

    @Test
    void userPasswordTest(){
        assertEquals("dzew", pocketUser1.getPassword());
    }


    @Test
    void userEqualsHashcode(){
        PocketUser saved1 = userRepository.saveAndFlush(pocketUser1);
        entityManager.clear();
        PocketUser saved2 = userRepository.saveAndFlush(pocketUser2);
        entityManager.clear();

        assertNotEquals(saved1, saved2);
        assertEquals(saved1, saved1);

        assertEquals(saved1.hashCode(), saved1.hashCode());
    }

    @Test
    void tostringGetter(){
        PocketUser saved1 = userRepository.saveAndFlush(pocketUser1);
        entityManager.clear();

        String expected = "PocketUser{" +
                "id=" + "1" +
                ", password='" + "dzew" + '\'' +
                ", email='" + "asd@asd.com" + '\'' +
                '}';

        assertEquals(expected, saved1.toString());
    }


}
