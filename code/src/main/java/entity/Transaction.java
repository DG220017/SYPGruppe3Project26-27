package entity;

import DAO.CostType;
import DAO.TransactionType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public CostType getCostType() {
        return costType;
    }

    public void setCostType(CostType costType) {
        this.costType = costType;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
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
}
