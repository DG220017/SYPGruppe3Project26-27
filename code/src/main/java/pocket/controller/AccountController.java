package pocket.controller;

import pocket.entity.Account;
import org.springframework.web.bind.annotation.*;
import pocket.exception.AccountNotFoundException;
import pocket.service.AccountService;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
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

    @GetMapping("/getAccountbyId")
    public Account getAccountById(@RequestParam long id){
        try{
            return accountService.getAccountById(id);
        }catch (AccountNotFoundException e){
            // hier mommt noch die anpassung mit response entity
        }
        return null;
    }

}
