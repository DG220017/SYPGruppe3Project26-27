package controller;


import DAO.TransactionType;
import entity.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import service.TransactionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public static final Logger log = LoggerFactory.getLogger(TransactionController.class);
    
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getAllTransactions(){
        return transactionService.getAllTransactions();
    }

    @PostMapping("/addTransaction")
    public Transaction addTransaction(@PathVariable Transaction transaction){
         return transactionService.addTransaction(transaction);
    }

    @DeleteMapping("/removeTransaction")
    public void removeTransaction(@PathVariable Transaction transaction){
        transactionService.removeTransaction(transaction);
    }

    @GetMapping("/findbyName")
    public Transaction getTransactionByName(@PathVariable String name){
        return transactionService.getTransactionByName(name);
    }

    @GetMapping("/findbyID")
    public Optional<Transaction> getTransactionById(@PathVariable Long id){
       return transactionService.getTransactionById(id);
    }

    @GetMapping("findAllbyType")
    public List<Transaction> getAllTransactionsByType(@PathVariable TransactionType type){
        return transactionService.getTransactionsAllByType(type);
    }

}
