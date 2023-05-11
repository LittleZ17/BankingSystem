package midtermProject.BankingSystem.service;

import midtermProject.BankingSystem.model.Accounts.Account;
import midtermProject.BankingSystem.repository.AccountsRepository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceAccount {
    @Autowired
    AccountRepository accountRepository;

    public Account getAccountById(Integer number){
        Optional<Account> accountOptional = accountRepository.findById(number);
        if(accountOptional.isEmpty()) return null;
        return accountOptional.get();
    }
}
