package midtermProject.BankingSystem.repository.AccountsRepository;

import midtermProject.BankingSystem.model.Accounts.CheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Integer> {
}
