package pocket.moneychecker;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pocket.entity.Transaction;
import pocket.enums.CostType;
import pocket.enums.TransactionType;
import pocket.repository.TransactionRepository;

import java.time.LocalDate;

@SpringBootTest
@Transactional
public class TrasactionTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private Transaction transaction1;
    private Transaction transaction2;
    private LocalDate date = LocalDate.now();

    @BeforeEach
    void setup(){
        transaction1 = new Transaction("Couch", 120.76, "Ebay small price", date,
                CostType.EXPENSES, TransactionType.BILLS);
        transaction2 = new Transaction("Chair", 1600, "Big golden chair", date,
                CostType.EXPENSES, TransactionType.BILLS);
    }

    @Test
    void equalsAndHashcode(){
        Transaction saved1 = transactionRepository.saveAndFlush(transaction1);
        entityManager.clear();
        Transaction saved2 = transactionRepository.saveAndFlush(transaction2);
        entityManager.clear();

        Assertions.assertNotEquals(saved1, saved2);
        Assertions.assertEquals(saved1, saved1);
        Assertions.assertNotEquals(null, saved1);
        Assertions.assertEquals(saved1.hashCode(), saved1.hashCode());
    }

    @Test
    void toStringTest(){
        Transaction saved1 = transactionRepository.saveAndFlush(transaction1);
        entityManager.clear();

        String expected = "Transaction{" +
                "id=" + 1 +
                ", amount=" + 120.76 +
                ", notes='" + "Ebay small price" + '\'' +
                ", date=" + date +
                ", costType=" + CostType.EXPENSES +
                ", transactionType=" + TransactionType.BILLS +
                ", name=" + "Couch" +
                '}';

        Assertions.assertEquals(expected, saved1.toString());
    }
}
