package midtermProject.BankingSystem.model.Accounts;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import midtermProject.BankingSystem.embeddables.Money;
import midtermProject.BankingSystem.enums.Status;
import midtermProject.BankingSystem.model.Users.AccountHolders;

@Entity
@Data
@NoArgsConstructor
public class StudentChecking extends Account{
    public StudentChecking(Money balance, String secretKey, AccountHolders primaryOwner, Status status) {
        super(balance, secretKey, primaryOwner, status);
    }
}
