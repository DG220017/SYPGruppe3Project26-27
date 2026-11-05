package pocket.DTO;

import pocket.entity.Account;
import pocket.entity.Transaction;

import java.util.List;

public record AccountResponse (long id, String username, double balance, List<Transaction> transactions, PocketUserResponse pocketUserResponse){
    public static AccountResponse fromAcount(Account account){
        return new AccountResponse(account.getId(), account.getUsername(),account.getBalance(), account.getTransactions(), PocketUserResponse.fromPocketUser(account.getUser()));
    }
}
