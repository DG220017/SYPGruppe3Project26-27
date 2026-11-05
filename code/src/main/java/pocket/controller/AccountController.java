package pocket.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pocket.entity.Account;
import org.springframework.web.bind.annotation.*;
import pocket.exception.AccountNotFoundException;
import pocket.service.AccountService;
import pocket.service.TransactionService;

import java.util.List;

@RestController
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts(){
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.NO_CONTENT);
    }

    @PostMapping("/addAccount")
    public ResponseEntity<Account> addAccount(@RequestBody Account account){
        return new ResponseEntity<>(accountService.addAccount(account),HttpStatus.CREATED);
    }

    @DeleteMapping("/removeAccount/{id}")
    public ResponseEntity<Void> removeAccount(@PathVariable long id){
        try{
            accountService.removeAccount(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (AccountNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAccountbyId")
    public ResponseEntity<Account> getAccountById(@RequestParam long id){
        try{
            return new ResponseEntity<>(accountService.getAccountById(id), HttpStatus.OK);
        }catch (AccountNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Für JUnit Test zwecke
    public AccountService getAccountService() {
        return accountService;
    }
}
