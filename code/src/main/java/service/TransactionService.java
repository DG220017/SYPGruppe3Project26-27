package service;

import DAO.TransactionType;
import entity.Transaction;
import org.springframework.stereotype.Service;
import repository.TransactionRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    public void getTransactionsInTimespan(LocalDate from, LocalDate to){}

    public Transaction addTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    public Transaction removeTransaction(Transaction transaction){
        transactionRepository.delete(transaction);
        return transaction;
    }

    public Transaction getTransactionById(long id){
        return transactionRepository.findById(id);
    }

    public Transaction getTransactionByName(String name){
        return transactionRepository.findByName(name);
    }

    public List<Transaction> getTransactionsAllByType(TransactionType type){
        return transactionRepository.findAllByTransactionType(type);
    }

}
