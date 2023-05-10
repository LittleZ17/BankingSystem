package midtermProject.BankingSystem.Service.AccountsService;

import midtermProject.BankingSystem.controller.dto.CheckingAccountDTO;
import midtermProject.BankingSystem.embeddables.Money;
import midtermProject.BankingSystem.model.Accounts.Account;
import midtermProject.BankingSystem.model.Accounts.CheckingAccount;
import midtermProject.BankingSystem.model.Accounts.StudentChecking;
import midtermProject.BankingSystem.model.Users.AccountHolders;
import midtermProject.BankingSystem.repository.AccountsRepository.CheckingAccountRepository;
import midtermProject.BankingSystem.repository.AccountsRepository.StudentCheckingRepository;
import midtermProject.BankingSystem.repository.UsersRepository.AccountHoldersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Period;

@Service
public class AccountService {
    @Autowired
    AccountHoldersRepository accountHoldersRepository;
    @Autowired
    CheckingAccountRepository checkingAccountRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;
    public Account saveCheckingAccount(CheckingAccount checkingAccount) {
        Money balance = new Money(checkingAccount.getBalance().getAmount());
        String secretKey = checkingAccount.getSecretKey();
        AccountHolders primaryOwner = accountHoldersRepository.findById(checkingAccount.getPrimaryOwner().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        int age = Period.between(primaryOwner.getDateOfBirth(), LocalDate.now()).getYears();
        if (age < 18) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Must be 18 years old to be the primary account owner");
        } else if (age >= 18 && age < 24) {
            return studentCheckingRepository.save(new StudentChecking(balance, secretKey, primaryOwner));
        } else {
            return checkingAccountRepository.save(new CheckingAccount(balance, secretKey, primaryOwner));
        }
    }
}
