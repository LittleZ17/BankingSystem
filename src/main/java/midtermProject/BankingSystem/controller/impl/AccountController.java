package midtermProject.BankingSystem.controller.impl;

import jakarta.validation.Valid;
import midtermProject.BankingSystem.controller.dto.AccountBalanceDTO;
import midtermProject.BankingSystem.controller.dto.AccountStatusDTO;
import midtermProject.BankingSystem.controller.interfaces.IAccountController;
import midtermProject.BankingSystem.embeddables.Money;
import midtermProject.BankingSystem.model.Accounts.*;
import midtermProject.BankingSystem.repository.AccountsRepository.*;
import midtermProject.BankingSystem.service.impl.ServiceAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class AccountController implements IAccountController {
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

    @GetMapping("/balance/{number}")
    @ResponseStatus(HttpStatus.OK)
    public Money getAccountBalance( @PathVariable Integer number) {
        return serviceAccount.getAccountBalance(number);
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
        return serviceAccount.saveCheckingAccount(checkingAccount);
    }

    @PostMapping("/accounts/student-checking")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentChecking saveStudentChecking(@RequestBody CheckingAccount checkingAccount){
       return serviceAccount.saveStudentChecking(checkingAccount);
    }

    @PostMapping("/accounts/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings saveSavings(@RequestBody Savings savings){
        return serviceAccount.saveSavings(savings);
    }

    @PostMapping("/accounts/credit-cards")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard saveCreditCard(@RequestBody CreditCard creditCard) {
        return serviceAccount.saveCreditCard(creditCard);
    }

    /* *********** PATCH *********** */

    @PatchMapping("/accounts/balance/{number}")
    @ResponseStatus(HttpStatus.OK)
    public Account updateAccountBalance(@PathVariable Integer number, @RequestBody @Valid AccountBalanceDTO accountBalanceDTO) {
        return serviceAccount.changeAccountBalance(number,accountBalanceDTO);
    }
    @PatchMapping("/accounts/change-status/{number}")
    @ResponseStatus(HttpStatus.OK)
    public Account updateAccountStatus(@PathVariable Integer number, @RequestBody @Valid AccountStatusDTO accountStatusDTO) {
        return serviceAccount.changeAccountStatus(number,accountStatusDTO);
    }

    /* *********** DELETE *********** */

    @DeleteMapping("/accounts/delete/{number}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable Integer number) {
        serviceAccount.deleteAccount(number);
    }

}
