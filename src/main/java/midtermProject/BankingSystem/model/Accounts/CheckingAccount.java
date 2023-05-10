package midtermProject.BankingSystem.model.Accounts;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import midtermProject.BankingSystem.embeddables.Money;

import midtermProject.BankingSystem.model.Users.AccountHolders;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "number")
@NoArgsConstructor
public class CheckingAccount extends Account{

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "min_balance_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "min_balance_amount"))
    })
    private Money minimumBalance;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "maintenance_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "maintenance_amount"))
    })
    private LocalDate lastFeeDate;
    public CheckingAccount(Money balance, String secretKey, AccountHolders primaryOwner) {
        super(balance, secretKey, primaryOwner);
        this.lastFeeDate = LocalDate.now();
    }
}
