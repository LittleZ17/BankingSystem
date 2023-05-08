package midtermProject.BankingSystem.model.Accounts;

import jakarta.persistence.*;
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
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer number;
    @Embedded
    private Money balance;
    private String secretKey;
    @ManyToOne
    @JoinColumn(name = "primary_owner_id")
    private AccountHolders primaryOwner;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="amount", column=@Column(name="penalty_fee_amount")),
            @AttributeOverride(name="currency", column=@Column(name="penalty_fee_currency"))
    })
    private final Money penaltyFee = new Money(BigDecimal.valueOf(40));
    private final LocalDate creationDate = LocalDate.now();
    @Enumerated(EnumType.STRING)
    private Status status;


        public Account(Money balance, String secretKey, AccountHolders primaryOwner, Status status) {
        this.balance = balance;
        this.secretKey = secretKey;
        this.primaryOwner = primaryOwner;
        this.status = Status.ACTIVE;
    }
}
