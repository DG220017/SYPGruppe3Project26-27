package service;

import DAO.TransactionType;
import entity.Transaction;
import repository.TransactionRepository;

import java.time.LocalDate;
import java.util.List;

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

        return null;
    }

    public Transaction removeTransaction(Transaction transaction){

        return null;
    }

    public Transaction getTransactionByName(String name){
        return null;
    }

    public List<Transaction> getTransactionsAllByType(TransactionType type){
        return null;
    }
}
