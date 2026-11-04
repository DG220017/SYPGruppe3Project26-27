package pocket.service;

import jakarta.transaction.Transactional;
import pocket.entity.Account;
import org.springframework.stereotype.Service;
import pocket.exception.AccountNotFoundException;
import pocket.repository.AccountRepository;

import java.beans.Transient;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Transactional
    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }

    @Transactional
    public Account removeAccount(long id) {
        Account account = getAccountById(id);
        accountRepository.delete(account);
        return account;
    }

    @Transactional
    public Account getAccountById(long id){
        return accountRepository.findById(id).orElseThrow(()->{
            return new AccountNotFoundException("Account with ID: "+id+" could not be found.");
        });
    }

}
