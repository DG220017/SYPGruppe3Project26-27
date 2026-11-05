package pocket.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pocket.DTO.AccountResponse;
import pocket.entity.Account;
import org.springframework.web.bind.annotation.*;
import pocket.exception.AccountNotFoundException;
import pocket.service.AccountService;

import java.util.List;

@RestController
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccounts(){
        return new ResponseEntity<>(accountService.getAllAccounts().stream().map(AccountResponse::fromAcount).toList(), HttpStatus.OK);
    }

    @PostMapping("/addAccount")
    public ResponseEntity<AccountResponse> addAccount(@RequestBody Account account){
        return new ResponseEntity<>(AccountResponse.fromAcount(accountService.addAccount(account)),HttpStatus.CREATED);
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

    @GetMapping("/getAccountbyId/{id}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable long id){
        try{
            return new ResponseEntity<>(AccountResponse.fromAcount(accountService.getAccountById(id)), HttpStatus.OK);
        }catch (AccountNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Für JUnit Test zwecke
    public AccountService getAccountService() {
        return accountService;
    }
}
