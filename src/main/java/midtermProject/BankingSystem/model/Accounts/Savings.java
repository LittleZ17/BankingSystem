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
public class Savings extends Account{
    @Embedded
    private Money minimumBalance = new Money(BigDecimal.valueOf(1000));
    private BigDecimal interestRate = BigDecimal.valueOf(0.0025);
    private LocalDate lastInterestDate = LocalDate.now();

    public Savings(Money balance, String secretKey, AccountHolders primaryOwner, Status status, Money minimumBalance, BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, status);
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
    }
}
