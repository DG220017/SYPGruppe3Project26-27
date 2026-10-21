package pocket.moneychecker;

import controller.TransactionController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import repository.TransactionRepository;
import service.AccountService;
import service.TransactionService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TransactionService transactionService;


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
