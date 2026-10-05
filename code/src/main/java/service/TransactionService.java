package service;

import entity.Transaction;
import repository.TransactionRepository;

public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction addTransaction(Transaction transaction){
        transactionRepository.save(transaction);
        return transaction;
    }

    public Transaction deleTransaction(Transaction transaction){
        transactionRepository.delete(transaction);
        return transaction;
    }

    public Transaction getTransactionById(long id){
        return transactionRepository.findById(id);
    }

}
