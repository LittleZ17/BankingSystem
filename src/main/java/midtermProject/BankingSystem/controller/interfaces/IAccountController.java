package midtermProject.BankingSystem.controller.interfaces;

import midtermProject.BankingSystem.embeddables.Money;
import midtermProject.BankingSystem.model.Accounts.*;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

public interface IAccountController {
    List<Account> getAccounts();
    Account getAccount(Integer number);
    Money getAccountBalance(Integer number);
    List<CheckingAccount> getCheckingAccounts();
    List<StudentChecking> getStudentChecking();
    List<Savings> getSavings();
    List<CreditCard> getCreditCards();
    Account saveCheckingAccount(CheckingAccount checkingAccount);
    StudentChecking saveStudentChecking(CheckingAccount checkingAccount);
    Savings saveSavings(Savings savings);
    CreditCard saveCreditCard(CreditCard creditCard);




    }
