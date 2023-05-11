package midtermProject.BankingSystem.repository.OperationRepository;

import midtermProject.BankingSystem.model.Operations.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
