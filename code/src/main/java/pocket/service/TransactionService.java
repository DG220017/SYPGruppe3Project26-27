package pocket.service;

import jakarta.transaction.Transactional;
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

    @Transactional
    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    //To be done!
    @Transactional
    public void getTransactionsInTimespan(LocalDate from, LocalDate to){}

    @Transactional
    public Transaction addTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    @Transactional
    public Transaction removeTransaction(long id){
        Transaction transaction = getTransactionById(id);
        transactionRepository.delete(transaction);
        return transaction;
    }

    @Transactional
    public Transaction getTransactionById(long id){

        return transactionRepository.findById(id).orElseThrow(()->
        {
            throw new IllegalArgumentException("Transaction with Id: "+ id +" could not be found.");
        });
    }

    @Transactional
    public Transaction getTransactionByName(String name){
        return transactionRepository.findByName(name).orElseThrow(()->{
            return new TransactionNotFoundException("Transaction with name: "+name+" could not be found.");
        });
    }

    @Transactional
    public List<Transaction> getTransactionsAllByType(TransactionType type){
        return transactionRepository.findAllByTransactionType(type);
    }

}
