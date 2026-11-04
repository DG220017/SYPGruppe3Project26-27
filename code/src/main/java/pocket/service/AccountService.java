package pocket.service;

import pocket.entity.Account;
import org.springframework.stereotype.Service;
import pocket.exception.AccountNotFoundException;
import pocket.repository.AccountRepository;

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

    public Account getAccountById(long id){
        return accountRepository.findById(id).orElseThrow(()->{
            return new AccountNotFoundException("Account with ID: "+id+" could not be found.");
        });
    }

}
