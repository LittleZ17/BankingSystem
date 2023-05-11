package midtermProject.BankingSystem.model.Accounts;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import midtermProject.BankingSystem.embeddables.Money;

import midtermProject.BankingSystem.model.Users.AccountHolders;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
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

    public boolean isBalanceBelowMinimum() {
        return getBalance().getAmount().compareTo(getMinimumBalance().getAmount()) < 0;
    }
    public void deductPenaltyFee() {
        if (isBalanceBelowMinimum()) {
            getBalance().getAmount().subtract(getPenaltyFee().getAmount());
        }
    }

    public CheckingAccount(Money balance, String secretKey, AccountHolders primaryOwner) {
        super(balance, secretKey, primaryOwner);
        this.lastFeeDate = LocalDate.now();
    }
}
