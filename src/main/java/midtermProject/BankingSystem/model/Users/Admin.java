package midtermProject.BankingSystem.model.Users;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@PrimaryKeyJoinColumn(name="id")
@Data
@NoArgsConstructor
public class Admin extends User{
    public Admin(String username, String password, String name) {
        super(username, password, name);
    }
}
