package midtermProject.BankingSystem.service.interfaces;

import jakarta.validation.Valid;
import midtermProject.BankingSystem.controller.dto.AccountBalanceDTO;
import midtermProject.BankingSystem.controller.dto.AccountStatusDTO;
import midtermProject.BankingSystem.model.Accounts.Account;
import midtermProject.BankingSystem.model.Accounts.CheckingAccount;
import midtermProject.BankingSystem.model.Accounts.CreditCard;
import midtermProject.BankingSystem.model.Accounts.Savings;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IServiceAccount {
    Account getAccountById(Integer number);

    Account saveCheckingAccount(CheckingAccount checkingAccount);

    Savings saveSavings(Savings savings);

    CreditCard saveCreditCard(CreditCard creditCard);

    Account updateAccountBalance(Integer number, AccountBalanceDTO accountBalanceDTO);

    Account updateAccountStatus(Integer id, AccountStatusDTO accountStatusDTO);

    Account deleteAccount(Integer id);
}
