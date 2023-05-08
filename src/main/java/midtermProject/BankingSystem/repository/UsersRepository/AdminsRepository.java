package midtermProject.BankingSystem.repository.UsersRepository;

import midtermProject.BankingSystem.model.Users.Admins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminsRepository extends JpaRepository<Admins, Integer> {
}
