package pocket.controller;


import pocket.DAO.TransactionType;
import pocket.entity.Transaction;

import org.springframework.web.bind.annotation.*;
import pocket.exception.TransactionNotFoundException;
import pocket.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    
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

    @GetMapping("/findTransactionbyName")
    public Transaction getTransactionByName(@PathVariable String name){
        return transactionService.getTransactionByName(name);
    }

    @GetMapping("/findTransactionbyID")
    public Transaction getTransactionById(@PathVariable Long id){
        try{
            return transactionService.getTransactionById(id);
        }catch (TransactionNotFoundException e){
            // Response entity dann säter
        }
        return null;
    }

    @GetMapping("findAllTransactionsbyType")
    public List<Transaction> getAllTransactionsByType(@PathVariable TransactionType type){
        return transactionService.getTransactionsAllByType(type);
    }

    // Für JUnit Test zwecke
    public TransactionService getTransactionService() {
        return transactionService;
    }
}
