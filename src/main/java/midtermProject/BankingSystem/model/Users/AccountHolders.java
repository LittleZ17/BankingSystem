package midtermProject.BankingSystem.model.Users;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import midtermProject.BankingSystem.embeddables.Address;
import midtermProject.BankingSystem.model.Accounts.Account;

import java.time.LocalDate;
import java.util.List;
@Entity
@PrimaryKeyJoinColumn(name="id")
@Data
@NoArgsConstructor
public class AccountHolders extends User{

    //@DateTimeFormat(pattern = "dd/MM/yyy")
    private LocalDate dateOfBirth;
    @Embedded
    private Address primaryAddress;
    @OneToMany(mappedBy = "primaryOwner", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Account> accounts;

    public AccountHolders(String username, String password, String name, LocalDate dateOfBirth, Address primaryAddress) {
        super(username, password, name);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
    }
}
