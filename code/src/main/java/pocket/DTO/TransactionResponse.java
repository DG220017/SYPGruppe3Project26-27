package pocket.DTO;

import pocket.entity.Transaction;
import pocket.enums.CostType;
import pocket.enums.TransactionType;

import java.time.LocalDate;

public record TransactionResponse (Long id, double amount, String notes, String name, LocalDate date, CostType costType, TransactionType transactionType){
    public static TransactionResponse fromTransaction(Transaction transaction){
        return new TransactionResponse(transaction.getId(), transaction.getAmount(), transaction.getNotes(), transaction.getName(),transaction.getDate(),transaction.getCostType(),transaction.getTransactionType());
    }
}
