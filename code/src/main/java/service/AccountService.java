package service;

import entity.Account;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import repository.AccountRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account removeAccount(Account account) {
        accountRepository.delete(account);
        return account;
    }

    public Optional<Account> getAccountById(long id){
        return accountRepository.findById(id);
    }
}
