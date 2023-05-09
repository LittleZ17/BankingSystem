package midtermProject.BankingSystem.model.Accounts;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.NoArgsConstructor;
import midtermProject.BankingSystem.embeddables.Money;
import midtermProject.BankingSystem.enums.Status;
import midtermProject.BankingSystem.model.Users.AccountHolders;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "number")
@Data
@NoArgsConstructor
public class CreditCard extends Account{
    @Embedded
    private Money creditLimit;

    private final BigDecimal interestRate = BigDecimal.valueOf(0.0025);
    private LocalDate lastInterestDate = LocalDate.now();

    public CreditCard(Money balance, String secretKey, AccountHolders primaryOwner, Money creditLimit, LocalDate lastInterestDate) {
        super(balance, secretKey, primaryOwner);
        this.creditLimit = creditLimit;
        this.lastInterestDate = lastInterestDate;
    }
}
