package pocket.repository;

import pocket.DAO.TransactionType;
import pocket.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {


    Optional<Transaction> findByName(String name);

    List<Transaction> findAllByTransactionType(TransactionType type);
}
