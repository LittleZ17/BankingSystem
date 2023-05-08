package midtermProject.BankingSystem.model.Users;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@PrimaryKeyJoinColumn(name = "id")
@NoArgsConstructor
public class Admins extends User{
    public Admins(String username, String password, String name) {
        super(username, password, name);
    }
}
