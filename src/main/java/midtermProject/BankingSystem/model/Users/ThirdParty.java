package midtermProject.BankingSystem.model.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@PrimaryKeyJoinColumn(name="id")
@Data
@NoArgsConstructor
public class ThirdParty extends User{

    @Column(unique = true, nullable = false)
    private String hashedKey;

    public ThirdParty(String username, String password, String name, String hashedKey) {
        super(username, password, name);
        this.hashedKey = hashedKey;
    }
}
