package midtermProject.BankingSystem.model.Accounts;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.NoArgsConstructor;
import midtermProject.BankingSystem.embeddables.Money;
import midtermProject.BankingSystem.model.Users.AccountHolders;

@Entity
@PrimaryKeyJoinColumn(name = "number")
@Data
@NoArgsConstructor
public class StudentChecking extends Account{

    public StudentChecking(Money balance, String secretKey, AccountHolders primaryOwner) {
        super(balance, secretKey, primaryOwner);
    }
}
