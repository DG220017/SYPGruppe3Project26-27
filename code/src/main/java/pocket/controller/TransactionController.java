package pocket.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.NO_CONTENT);
    }

    @PostMapping("/addTransaction")
    public ResponseEntity<Transaction> addTransaction(@PathVariable Transaction transaction){
         return new ResponseEntity<>(transactionService.addTransaction(transaction), HttpStatus.CREATED);
    }

    @DeleteMapping("/removeTransaction")
    public ResponseEntity removeTransaction(@PathVariable Transaction transaction){
        transactionService.removeTransaction(transaction);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findTransactionbyName")
    public ResponseEntity<Transaction> getTransactionByName(@PathVariable String name){
        try{
            return new ResponseEntity<>(transactionService.getTransactionByName(name),HttpStatus.OK);
        }catch (TransactionNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findTransactionbyID")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(transactionService.getTransactionById(id),HttpStatus.NO_CONTENT);
        }catch (TransactionNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("findAllTransactionsbyType")
    public ResponseEntity<List<Transaction>> getAllTransactionsByType(@PathVariable TransactionType type){
        return new ResponseEntity<>(transactionService.getTransactionsAllByType(type), HttpStatus.NO_CONTENT);
    }

    // Für JUnit Test zwecke
    public TransactionService getTransactionService() {
        return transactionService;
    }
}
