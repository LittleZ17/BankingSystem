package midtermProject.BankingSystem.service.impl;

import midtermProject.BankingSystem.controller.dto.AccountBalanceDTO;
import midtermProject.BankingSystem.controller.dto.AccountStatusDTO;
import midtermProject.BankingSystem.embeddables.Money;
import midtermProject.BankingSystem.model.Accounts.*;
import midtermProject.BankingSystem.model.Users.AccountHolders;
import midtermProject.BankingSystem.repository.AccountsRepository.*;
import midtermProject.BankingSystem.repository.UsersRepository.AccountHoldersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class ServiceAccount {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountHoldersRepository accountHoldersRepository;
    @Autowired
    CheckingAccountRepository checkingAccountRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;
    @Autowired
    SavingRepository savingRepository;
    @Autowired
    CreditCardRepository creditCardRepository;

    public Account getAccountById(Integer number){
        Optional<Account> accountOptional = accountRepository.findById(number);
        if(accountOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Account is not found") ;
        }
        return accountOptional.get();
    }
    public Money getAccountBalance(Integer id) {
        Account account = getAccountById(id);
        return account.getBalance();
    }

    public Account changeAccountBalance(Integer id, AccountBalanceDTO accountBalanceDTO) {
        Account newAccountBalance = getAccountById(id);
        try {
            newAccountBalance.setBalance(new Money(accountBalanceDTO.getBalance(), accountBalanceDTO.getCurrency()));
            accountRepository.save(newAccountBalance);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This balance is not valid");
        }
        return newAccountBalance;
    }

    public Account changeAccountStatus(Integer id, AccountStatusDTO accountStatusDTO) {
        Account newAccountStatus = getAccountById(id);
        try {
            newAccountStatus.setStatus(accountStatusDTO.getStatus());
            accountRepository.save(newAccountStatus);
        } catch (Exception e) {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "This status is not valid");
        }
        return newAccountStatus;
    }

    public Account saveCheckingAccount(CheckingAccount checkingAccount){
        String secretKey = checkingAccount.getSecretKey();
        AccountHolders primaryOwner = accountHoldersRepository.findById(checkingAccount.getPrimaryOwner().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Primary Owner is not found"));

        Money balance;
        if (checkingAccount.getBalance().getAmount().compareTo(checkingAccount.getMinimumBalance().getAmount()) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The balance must be greater than or equal to the minimum required balance 250 USD") ;
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

    public StudentChecking saveStudentChecking(CheckingAccount checkingAccount){

        Money balance = new Money(checkingAccount.getMinimumBalance().getAmount());
        String secretKey = checkingAccount.getSecretKey();
        AccountHolders primaryOwner = accountHoldersRepository.findById(checkingAccount.getPrimaryOwner().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Primary Owner is not found"));

        StudentChecking studentChecking = new StudentChecking(balance, secretKey, primaryOwner);
        return studentCheckingRepository.save(studentChecking);
    }

    public Savings saveSavings(Savings savings){

        String secretKey = savings.getSecretKey();
        AccountHolders primaryOwner = accountHoldersRepository.findById(savings.getPrimaryOwner().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Primary Owner is not found"));

        Money balance;
        BigDecimal balanceCredit = (savings.getBalance().getAmount() != null) ? savings.getBalance().getAmount() : new BigDecimal("1000");

        if (balanceCredit.compareTo(new BigDecimal("100")) < 0 || balanceCredit.compareTo(new BigDecimal("1000")) > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The balance must be between 100 and 1000.");
        } else {
            balance = new Money(balanceCredit);
        }

        BigDecimal interestRate = (savings.getInterestRate() != null) ? savings.getInterestRate() : new BigDecimal("0.0025");
        if (interestRate.compareTo(new BigDecimal("0.5")) > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The interest rate exceeds 0.5 that is the maximum allowed rate.");
        }
        Savings savingAccount = new Savings(balance, secretKey, primaryOwner, interestRate);

        return savingRepository.save(savingAccount);
    }

    public CreditCard saveCreditCard(CreditCard creditCard){

        String secretKey = creditCard.getSecretKey();
        AccountHolders primaryOwner = accountHoldersRepository.findById(creditCard.getPrimaryOwner().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Primary Owner is not found"));

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


    public void deleteAccount(Integer number) {
        Optional<Account> accountOptional = accountRepository.findById(number);
        if(accountOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Account is not found") ;
        }
        accountRepository.delete(accountOptional.get());
    }
}
