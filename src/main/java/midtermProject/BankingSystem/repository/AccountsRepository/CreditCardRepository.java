package midtermProject.BankingSystem.repository.AccountsRepository;

import midtermProject.BankingSystem.model.Accounts.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {
}
