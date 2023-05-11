package midtermProject.BankingSystem.controller.impl;

import midtermProject.BankingSystem.embeddables.Money;
import midtermProject.BankingSystem.model.Accounts.*;
import midtermProject.BankingSystem.model.Users.AccountHolders;
import midtermProject.BankingSystem.repository.AccountsRepository.*;
import midtermProject.BankingSystem.repository.UsersRepository.AccountHoldersRepository;
import midtermProject.BankingSystem.service.ServiceAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

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


    @Autowired
    AccountHoldersRepository accountHolderRepository;

    @Autowired
    ServiceAccount serviceAccount;

    /* *********** GET *********** */
    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }
    @GetMapping("/accounts/{number}")
    @ResponseStatus(HttpStatus.OK)
    public Account getAccount(@PathVariable Integer number){
        return serviceAccount.getAccountById(number);
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

    @GetMapping("/accounts/credit-cards")
    @ResponseStatus(HttpStatus.OK)
    public List<CreditCard> getCreditCards(){
        return creditCardRepository.findAll();
    }


    /* *********** POST *********** */

    @PostMapping("/accounts/checking-accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public Account saveCheckingAccount(@RequestBody CheckingAccount checkingAccount){
        String secretKey = checkingAccount.getSecretKey();
        AccountHolders primaryOwner = accountHolderRepository.findById(checkingAccount.getPrimaryOwner().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Money balance;
        if (checkingAccount.getBalance().getAmount().compareTo(checkingAccount.getMinimumBalance().getAmount()) <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The balance must be greater than or equal to the minimum required balance..") ;
        } else {
            balance = new Money(checkingAccount.getBalance().getAmount());
        }
        int age = Period.between(primaryOwner.getDateOfBirth(), LocalDate.now()).getYears();
        if (age < 18) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Must be 18 years old to be the primary account owner");
        } else if (age >= 18 && age < 24) {
            StudentChecking studentChecking = new StudentChecking(balance, secretKey, primaryOwner);
            return studentCheckingRepository.save(studentChecking);
        } else {
            CheckingAccount checkingAccountToSave = new CheckingAccount(balance, secretKey, primaryOwner);
            return checkingAccountRepository.save(checkingAccountToSave);
        }
    }


    @PostMapping("/accounts/student-checking")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentChecking saveStudentChecking(@RequestBody CheckingAccount checkingAccount){

        Money balance = new Money(checkingAccount.getMinimumBalance().getAmount());
        String secretKey = checkingAccount.getSecretKey();
        AccountHolders primaryOwner = accountHolderRepository.findById(checkingAccount.getPrimaryOwner().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        StudentChecking studentChecking = new StudentChecking(balance, secretKey, primaryOwner);
        return studentCheckingRepository.save(studentChecking);
    }

 /*   @PostMapping("/accounts/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings saveSavings(@RequestBody Savings savings){

        String secretKey = savings.getSecretKey();

        AccountHolders primaryOwner = accountHolderRepository.findById(savings.getPrimaryOwner().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Money balance;

            BigDecimal minimumBalance = (savings.getMinimumBalance() != null) ? savings.getMinimumBalance().getAmount() : new BigDecimal("1000");
            if (minimumBalance.compareTo(new BigDecimal("100")) < 0 || minimumBalance.compareTo(new BigDecimal("1000")) > 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The minimum balance must be between 100 and 1000.");
            }
            balance = new Money(minimumBalance);
        } else {
            BigDecimal amount = savings.getBalance().getAmount();
            if (amount.compareTo(new BigDecimal("100")) < 0 || amount.compareTo(new BigDecimal("1000")) > 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The balance must be between 100 and 1000.");
            }
            balance = new Money(amount);
        }

        BigDecimal interestRate = (savings.getInterestRate() != null) ? savings.getInterestRate() : new BigDecimal("0.0025");
        if (interestRate.compareTo(new BigDecimal("0.5")) > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The interest rate exceeds 0.5 that is the maximum allowed rate.");
        }
        Savings savingAccount = new Savings(balance, secretKey, primaryOwner, interestRate);

        return savingRepository.save(savingAccount);
    }*/
    @PostMapping("/accounts/credit-cards")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard saveCreditCard(@RequestBody CreditCard creditCard){

        String secretKey = creditCard.getSecretKey();
        AccountHolders primaryOwner = accountHolderRepository.findById(creditCard.getPrimaryOwner().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Money creditLimit = (creditCard.getCreditLimit().getAmount() != null) ? creditCard.getCreditLimit() : new Money(new BigDecimal(100.0));
        if (creditLimit.getAmount().compareTo(new BigDecimal("100")) < 0 || creditLimit.getAmount().compareTo(new BigDecimal("100000")) > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The credit limit must be between 100 and 100000.");
        }

        BigDecimal interestRate = (creditCard.getInterestRate() != null) ? creditCard.getInterestRate() : new BigDecimal("0.2");
        if (interestRate.compareTo(new BigDecimal("0.1")) < 0 || interestRate.compareTo(new BigDecimal("0.2")) > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The interest rate must be between 0.1 and 0.2.");
        }

        Money balance = new Money(BigDecimal.ZERO);

        CreditCard creditCardAccount = new CreditCard(balance, secretKey, primaryOwner,creditLimit,interestRate);

        return creditCardRepository.save(creditCardAccount);
    }
}
