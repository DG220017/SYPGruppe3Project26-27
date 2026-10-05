package entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany
    @JoinColumn(name = "transactions_id")
    private List<Transaction> transactions= new ArrayList<>();

    private String username;

    private double balance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Account() {

    }

    public User getUser() {
        return user;
    }

    public Account(int id, String username, double balance) {
        this.id = id;
        this.username = username;
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

    public void removeTransaction(Transaction transaction){
        transactions.remove(transaction);
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Account account)) return false;
        return id == account.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
