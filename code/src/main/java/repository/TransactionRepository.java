package repository;

import DAO.TransactionType;
import entity.Transaction;
import org.hibernate.internal.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {


    Optional<Transaction> findById(long id);

    Optional <Transaction> findByName(String name);

    List<Transaction> findAllByTransactionType(TransactionType type);
}
