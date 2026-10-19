package entity;

import DAO.CostType;
import DAO.TransactionType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "transactions")
public class Transaction implements Comparable<Transaction>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double amount;

    private String notes;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private CostType costType;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    public Transaction() {

    }

    public Transaction(int id, double amount, String notes, LocalDate date,
                       CostType costType, TransactionType transactionType) {
        this.id = id;
        this.amount = amount;
        this.notes = notes;
        this.date = date;
        this.costType = costType;
        this.transactionType = transactionType;
    }

    public long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }


    public String getNotes() {
        return notes;
    }


    public LocalDate getDate() {
        return date;
    }


    public CostType getCostType() {
        return costType;
    }


    public TransactionType getTransactionType() {
        return transactionType;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Transaction that)) return false;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + getId() +
                ", amount=" + getAmount() +
                ", notes='" + getNotes() + '\'' +
                ", date=" + getDate() +
                ", costType=" + getCostType() +
                ", transactionType=" + getTransactionType() +
                '}';
    }


    @Override
    public int compareTo(Transaction o) {
        return this.date.compareTo(o.date);
    }
}
