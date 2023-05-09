package midtermProject.BankingSystem.repository.UsersRepository;

import midtermProject.BankingSystem.model.Users.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
