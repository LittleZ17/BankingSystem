package midtermProject.BankingSystem.model.Operations;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import midtermProject.BankingSystem.embeddables.Money;
import midtermProject.BankingSystem.model.Accounts.Account;
import midtermProject.BankingSystem.model.Users.ThirdParty;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    private Integer sourceAccountNumber;
    private Integer destinationAccountNumber;

    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "transaction_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "transaction_currency"))
    })
    private Money amount;


    private LocalDate transactionDate;
    private String hashedKey;

    public Transaction(Integer sourceAccountNumber, Integer destinationAccountNumber, Money amount) {
        this.sourceAccountNumber = sourceAccountNumber;
        this.destinationAccountNumber = destinationAccountNumber;
        this.amount = amount;
        this.transactionDate = LocalDate.now();
    }

    /*public Transaction(Integer sourceAccountNumber, Integer destinationAccountNumber, Money amount, String hashedKey) {
        this.sourceAccountNumber = sourceAccountNumber;
        this.destinationAccountNumber = destinationAccountNumber;
        this.amount = amount;
        this.transactionDate = LocalDate.now();
        this.hashedKey = hashedKey;
    }*/
}
