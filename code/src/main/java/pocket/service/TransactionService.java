package pocket.service;

import jakarta.transaction.Transactional;
import pocket.DTO.TransactionResponse;
import pocket.enums.TransactionType;
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
    public List<TransactionResponse> getAllTransactions(){
        return transactionRepository.findAll().stream().sorted().map(TransactionResponse::fromTransaction).toList();
    }

    //To be done!
    @Transactional
    public void getTransactionsInTimespan(LocalDate from, LocalDate to){

    }

    @Transactional
    public TransactionResponse addTransaction(Transaction transaction){
        return TransactionResponse.fromTransaction(transactionRepository.save(transaction));
    }

    @Transactional
    public void removeTransaction(long id){
      Transaction transaction = getTransaction(id);
      transactionRepository.delete(transaction);
    }

    @Transactional
    public TransactionResponse getTransactionById(long id){

        return TransactionResponse.fromTransaction(transactionRepository.findById(id).orElseThrow(()->
        {
            throw new IllegalArgumentException("Transaction with Id: "+ id +" could not be found.");
        }));
    }

    @Transactional
    public TransactionResponse getTransactionByName(String name){
        return TransactionResponse.fromTransaction(transactionRepository.findByName(name).orElseThrow(()->{
            return new TransactionNotFoundException("Transaction with name: "+name+" could not be found.");
        }));
    }

    @Transactional
    public List<TransactionResponse> getTransactionsAllByType(TransactionType type){
        return transactionRepository.findAllByTransactionType(type).stream().sorted().map(TransactionResponse::fromTransaction).toList();
    }
    @Transactional
    private Transaction getTransaction(long id){

        return transactionRepository.findById(id).orElseThrow(()->
        {
            throw new IllegalArgumentException("Transaction with Id: "+ id +" could not be found.");
        });
    }
}
