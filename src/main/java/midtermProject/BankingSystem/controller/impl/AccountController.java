package midtermProject.BankingSystem.controller.impl;

import jakarta.validation.Valid;
import midtermProject.BankingSystem.Service.AccountsService.AccountService;
import midtermProject.BankingSystem.controller.dto.CheckingAccountDTO;
import midtermProject.BankingSystem.embeddables.Money;
import midtermProject.BankingSystem.model.Accounts.*;
import midtermProject.BankingSystem.model.Users.AccountHolders;
import midtermProject.BankingSystem.repository.AccountsRepository.*;
import midtermProject.BankingSystem.repository.UsersRepository.AccountHoldersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CheckingAccountRepository checkingAccountRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;
    @Autowired
    SavingRepository savingRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    /*-----*/
    @Autowired
    AccountService accountService;

    /* *********** GET *********** */
    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }
    @GetMapping("/accounts/{number}")
    @ResponseStatus(HttpStatus.OK)
    public Account getAccount(@PathVariable Integer number){
        Optional<Account> accountOptional = accountRepository.findById(number);
        if(accountOptional.isEmpty()) return null;
        return accountOptional.get();
    }

    @GetMapping("/accounts/checking-accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<CheckingAccount> getCheckingAccounts(){
        return checkingAccountRepository.findAll();
    }

    @GetMapping("/accounts/student-checking")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentChecking> getStudentChecking(){
        return studentCheckingRepository.findAll();
    }

    @GetMapping("/accounts/savings")
    @ResponseStatus(HttpStatus.OK)
    public List<Savings> getSavings(){
        return savingRepository.findAll();
    }

    @GetMapping("/accounts/credit-card")
    @ResponseStatus(HttpStatus.OK)
    public List<CreditCard> getCreditCards(){
        return creditCardRepository.findAll();
    }

    /* *********** POST *********** */
    @PostMapping("/accounts/checking-accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public Account saveCheckingAccount(@RequestBody CheckingAccount checkingAccount){
        return accountService.saveCheckingAccount(checkingAccount);
    }


    @PostMapping("/accounts/student-checking")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentChecking saveStudentChecking(@RequestBody StudentChecking studentChecking){
        return studentCheckingRepository.save(studentChecking);
    }

    @PostMapping("/accounts/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings saveSavings(@RequestBody Savings savings){
        return savingRepository.save(savings);
    }

    @PostMapping("/accounts/credit-card")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard savecreditCard(@RequestBody CreditCard creditCard){
        return creditCardRepository.save(creditCard);
    }
}
