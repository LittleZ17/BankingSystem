package midtermProject.BankingSystem.model.Accounts;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import midtermProject.BankingSystem.embeddables.Money;
import midtermProject.BankingSystem.enums.Status;
import midtermProject.BankingSystem.model.Users.AccountHolders;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class CreditCard extends Account{
    @Embedded
    private Money creditLimit;

    private BigDecimal interestRate = BigDecimal.valueOf(0.0025);
    private LocalDate lastInterestDate = LocalDate.now();

    public CreditCard(Money balance, String secretKey, AccountHolders primaryOwner, Status status, Money creditLimit, BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, status);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }
}
