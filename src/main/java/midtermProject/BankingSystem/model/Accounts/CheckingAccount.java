package midtermProject.BankingSystem.model.Accounts;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import midtermProject.BankingSystem.embeddables.Money;
import midtermProject.BankingSystem.enums.Status;
import midtermProject.BankingSystem.model.Users.AccountHolders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "number")
public class CheckingAccount extends Account{
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "min_balance_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "min_balance_amount"))
    })
    private final Money minimumBalance = new Money(BigDecimal.valueOf(250));
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "maintenance_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "maintenance_amount"))
    })
    private final Money monthlyMaintenanceFee = new Money(BigDecimal.valueOf(12));
    private LocalDate lastFeeDate;

      public CheckingAccount(Money balance, String secretKey, AccountHolders primaryOwner, Status status, Money minimumBalance, Money monthlyMaintenanceFee, Date lastFeeDate) {
        super(balance, secretKey, primaryOwner, status);
        this.lastFeeDate = LocalDate.now();
    }
}