package pocket.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pocket.enums.TransactionType;
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
        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
    }

    @PostMapping("/addTransaction")
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction){
         return new ResponseEntity<>(transactionService.addTransaction(transaction), HttpStatus.CREATED);
    }

    @DeleteMapping("/removeTransaction/{id}")
    public ResponseEntity removeTransaction(@PathVariable long id){
        try{
            transactionService.removeTransaction(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (TransactionNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findTransactionbyName/{name}")
    public ResponseEntity<Transaction> getTransactionByName(@PathVariable String name){
        try{
            return new ResponseEntity<>(transactionService.getTransactionByName(name),HttpStatus.OK);
        }catch (TransactionNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findTransactionbyID/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(transactionService.getTransactionById(id),HttpStatus.OK);
        }catch (TransactionNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("findAllTransactionsbyType/{type}")
    public ResponseEntity<List<Transaction>> getAllTransactionsByType(@PathVariable TransactionType type){
        return new ResponseEntity<>(transactionService.getTransactionsAllByType(type), HttpStatus.OK);
    }

    // Für JUnit Test zwecke
    public TransactionService getTransactionService() {
        return transactionService;
    }
}
