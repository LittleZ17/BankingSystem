package midtermProject.BankingSystem.repository.AccountsRepository;

import midtermProject.BankingSystem.model.Accounts.StudentChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCheckingRepository extends JpaRepository<StudentChecking, Integer> {
}
