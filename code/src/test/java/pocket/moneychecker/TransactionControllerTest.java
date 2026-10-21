package pocket.moneychecker;

import DAO.CostType;
import DAO.TransactionType;
import controller.TransactionController;
import entity.Transaction;
import org.hibernate.validator.constraints.Mod10Check;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import repository.TransactionRepository;
import service.AccountService;
import service.TransactionService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TransactionService service;

    @MockitoBean
    private TransactionRepository repository;

    private TransactionController controller;

    Transaction transaction1;
    Transaction transaction2;
    Transaction transaction3;


    @BeforeEach
    public void createTransaction(){
        transaction1 = new Transaction(1,120.5,"Test", LocalDate.of(2025,5,6), CostType.EXPENSES, TransactionType.BILLS);
        transaction2 = new Transaction(1,120.5,"Test", LocalDate.of(2025,5,6), CostType.EXPENSES, TransactionType.BILLS);
        transaction3 = new Transaction(1,120.5,"Test", LocalDate.of(2025,5,6), CostType.EXPENSES, TransactionType.BILLS);
    }

    @Test
    public void testTransactionControllerConstructor(){
        TransactionController c = new TransactionController(service);
        assertEquals(service, c.getTransactionService());
    }

    @Test
    public void testGetAllTransactions(){
        TransactionController c = new TransactionController(service);
     //   c.addTransaction(transaction);
        assertEquals(repository.findAll(),c.getAllTransactions());
    }



}
