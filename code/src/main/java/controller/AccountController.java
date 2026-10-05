package controller;

import entity.Account;
import org.springframework.web.bind.annotation.*;
import service.AccountService;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {

    private AccountService accountService;

    public void setAccountService(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    @PostMapping("/addAccount")
    public Account addAccount(@RequestBody Account account){
        return accountService.addAccount(account);
    }

    @DeleteMapping("/removeAccount")
    public Account removeAccount(@RequestBody Account account){
        return accountService.removeAccount(account);
    }

    @GetMapping("/{id}")
    public Optional<Account> getAccountById(@RequestParam long id){
        return accountService.getAccountById(id);
    }

}
