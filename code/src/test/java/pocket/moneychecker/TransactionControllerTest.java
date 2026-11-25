package pocket.moneychecker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pocket.DTO.TransactionResponse;
import pocket.enums.CostType;
import pocket.enums.TransactionType;
import pocket.controller.TransactionController;
import pocket.entity.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pocket.repository.TransactionRepository;
import pocket.service.TransactionService;

import java.awt.font.TransformAttribute;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
        transaction1 = new Transaction("t1", 120.5, "Test", LocalDate.of(2025, 5, 6), CostType.EXPENSES, TransactionType.BILLS);
        transaction2 = new Transaction("t2",120.5, "Test", LocalDate.of(2025, 5, 6), CostType.EXPENSES, TransactionType.FREIZEIT);
        transaction3 = new Transaction("t3", 120.5, "Test", LocalDate.of(2025, 5, 6), CostType.EXPENSES, TransactionType.BILLS);
    }

    @Test
    public void testUserControllerConstructor() {
        // Prüft, ob der Service korrekt im Controller sitzt
        assertEquals(service, controller.getTransactionService());
    }

    @Test
    public void testGetAllTransactions() {
        // Arrange
        List<TransactionResponse> expectedTransactions =
                List.of(TransactionResponse.fromTransaction(transaction1), TransactionResponse.fromTransaction(transaction2), TransactionResponse.fromTransaction(transaction3));

        when(service.getAllTransactions())
                .thenReturn(expectedTransactions);

        // Act
        ResponseEntity<List<TransactionResponse>> response =
                controller.getAllTransactions();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedTransactions, response.getBody());
    }

    @Test
    public void testAddTransaction() {
        TransactionResponse tr = TransactionResponse.fromTransaction(transaction1);
        // Arrange
        when(service.addTransaction(transaction1))
                .thenReturn(TransactionResponse.fromTransaction(transaction1));

        // Act
        ResponseEntity<TransactionResponse> response =
                controller.addTransaction(transaction1);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(tr, response.getBody());
    }

    @Test
    public void testRemoveTransaction(){
        // Act
        ResponseEntity<TransactionResponse> response =
                controller.removeTransaction(2);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
   }

    @Test
    public void testGetTransactionByName() {

        // Arrange
        Transaction transaction = new Transaction();
        TransactionResponse transaction1 = TransactionResponse.fromTransaction(transaction);
        when(service.getTransactionByName("t1"))
                .thenReturn(transaction1);

        // Act
        ResponseEntity<TransactionResponse> response =
                controller.getTransactionByName("t1");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transaction1, response.getBody());
    }

    @Test
    public void testGetTransactionById(){

        //Arrange
        Transaction transaction = new Transaction();
        TransactionResponse transaction1 = TransactionResponse.fromTransaction(transaction);
        when(service.getTransactionById(1L)).thenReturn(transaction1);

        //Act
        ResponseEntity<TransactionResponse> response =
                controller.getTransactionById(1L);

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transaction1, response.getBody());
    }

    @Test
    public void testGetAllTransactionsByType(){

        // Arrange
        List<TransactionResponse> expectedTransactions =
                List.of(TransactionResponse.fromTransaction(transaction1),TransactionResponse.fromTransaction(transaction3));

        when(service.getTransactionsAllByType(TransactionType.BILLS))
                .thenReturn(expectedTransactions);

        // Act
        ResponseEntity<List<TransactionResponse>> response =
                controller.getAllTransactionsByType(TransactionType.BILLS);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedTransactions, response.getBody());
    }

    @Test
    public void testGetTransactionService(){
        assertEquals(service, controller.getTransactionService());
    }
}