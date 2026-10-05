package repository;

import DAO.TransactionType;
import entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {


    Transaction findById(long id);

    Transaction findByName(String name);

    List<Transaction> findAllByTransactionType(TransactionType type);
}
