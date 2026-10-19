package pocket.moneychecker;
import entity.PocketUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    PocketUser pocketUser;

    @BeforeEach
    void setup(){
        pocketUser = new PocketUser(1, "dzew","asd@asd.com");
    }

    @Test
    void userEmailTest(){
        assertEquals("dzew", pocketUser.getEmail());
    }

    @Test
    void userPasswordTest(){
        assertEquals("dzew", pocketUser.getPassword());
    }

    @Test
    void userTest(){
        assertEquals("dzew", pocketUser.getPassword());
    }


}
