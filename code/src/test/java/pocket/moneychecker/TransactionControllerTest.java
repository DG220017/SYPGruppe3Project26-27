package pocket.moneychecker;

import controller.TransactionController;
import org.junit.jupiter.api.Test;
import repository.TransactionRepository;
import service.TransactionService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionControllerTest {

    private TransactionService t1;
    private TransactionRepository repository;
    private TransactionController controller;

    @Test
    public void testTransactionControllerConstructor(){
        TransactionController c = new TransactionController(t1);
        assertEquals(t1, c.getTransactionService());
    }

   @Test
    public void testGetAllTransactions(){
        assertEquals(repository.findAll(),controller.getAllTransactions());
   }

}
