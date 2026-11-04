package pocket.service;

import pocket.DAO.TransactionType;
import pocket.entity.Transaction;
import org.springframework.stereotype.Service;
import pocket.exception.TransactionNotFoundException;
import pocket.repository.TransactionRepository;

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

    //To be done!
    public void getTransactionsInTimespan(LocalDate from, LocalDate to){}

    public Transaction addTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    public Transaction removeTransaction(Transaction transaction){
        transactionRepository.delete(transaction);
        return transaction;
    }

    public Transaction getTransactionById(long id){

        return transactionRepository.findById(id).orElseThrow(()->
        {
            throw new IllegalArgumentException("Transaction with Id: "+ id +" could not be found.");
        });
    }

    public Transaction getTransactionByName(String name){
        return transactionRepository.findByName(name).orElseThrow(()->{
            return new TransactionNotFoundException("Transaction with name: "+name+" could not be found.");
        });
    }

    public List<Transaction> getTransactionsAllByType(TransactionType type){
        return transactionRepository.findAllByTransactionType(type);
    }

}
