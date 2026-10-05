package entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @OneToMany
    @JoinColumn(name = "account_id")
    private List<Account> accounts = new ArrayList<>();

    private String password;

    private String email;

    public List<Account> getAccount() {
        return accounts;
    }

    public User() {

    }

    public void addAccount(Account account){
        this.accounts.add(account);
    }

    public void removeAccount(Account account){
        this.accounts.remove(account);
    }


    public User(int id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
