package midtermProject.BankingSystem.repository.UsersRepository;

import midtermProject.BankingSystem.model.Users.AccountHolders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHoldersRepository extends JpaRepository<AccountHolders, Integer> {
}
