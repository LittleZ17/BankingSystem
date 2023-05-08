package midtermProject.BankingSystem.repository.AccountsRepository;

import midtermProject.BankingSystem.model.Accounts.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
