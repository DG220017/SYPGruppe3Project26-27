package pocket.moneychecker;

import DAO.CostType;
import DAO.TransactionType;
import controller.TransactionController;
import entity.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.TransactionRepository;
import service.TransactionService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // WICHTIG: Aktiviert Mockito für diese Klasse
public class TransactionControllerTest {

    @Mock
    private TransactionRepository repository;

    @Mock
    private TransactionService service;

    @InjectMocks
    private TransactionController controller; // Wird von Mockito initialisiert und erhält die Mocks injiziert

    Transaction transaction1;
    Transaction transaction2;
    Transaction transaction3;

    @BeforeEach
    public void createTransaction() {
        transaction1 = new Transaction(1, 120.5, "Test", LocalDate.of(2025, 5, 6), CostType.EXPENSES, TransactionType.BILLS);
        transaction2 = new Transaction(1, 120.5, "Test", LocalDate.of(2025, 5, 6), CostType.EXPENSES, TransactionType.BILLS);
        transaction3 = new Transaction(1, 120.5, "Test", LocalDate.of(2025, 5, 6), CostType.EXPENSES, TransactionType.BILLS);
    }

    @Test
    public void testTransactionControllerConstructor() {
        // Prüft, ob der Service korrekt im Controller sitzt
        assertEquals(service, controller.getTransactionService());
    }

    @Test
    public void testGetAllTransactions() {
        // Da 'service' ein Mock ist, müssen wir ihm sagen, was er zurückgeben soll
        List<Transaction> expectedTransactions = List.of(transaction1, transaction2, transaction3);
        when(service.getAllTransactions()).thenReturn(expectedTransactions);

        // Testen des Controller-Aufrufs
        assertEquals(expectedTransactions, controller.getAllTransactions());
    }
}