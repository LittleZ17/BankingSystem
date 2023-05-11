package midtermProject.BankingSystem.service.impl;

import jakarta.validation.constraints.NotNull;
import midtermProject.BankingSystem.controller.impl.AccountController;
import midtermProject.BankingSystem.model.Accounts.Account;
import midtermProject.BankingSystem.model.Accounts.Savings;
import midtermProject.BankingSystem.model.Operations.Transaction;
import midtermProject.BankingSystem.model.Users.ThirdParty;
import midtermProject.BankingSystem.repository.OperationRepository.TransactionRepository;
import midtermProject.BankingSystem.service.interfaces.IServiceTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceTransaction implements IServiceTransaction {
    @Autowired
    private AccountController accountController;
    @Autowired
    private ServiceAccount serviceAccount;
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction transaction) {

        Account sourceAccount = serviceAccount.getAccountById(transaction.getSourceAccountNumber());
        Account destinationAccount = serviceAccount.getAccountById(transaction.getDestinationAccountNumber());

        if (sourceAccount != null && destinationAccount != null) {
            if (sourceAccount.getBalance().getAmount().compareTo(transaction.getAmount().getAmount()) >= 0) {
                sourceAccount.getBalance().decreaseAmount(transaction.getAmount());
                destinationAccount.getBalance().increaseAmount(transaction.getAmount());

                transactionRepository.save(transaction);
            } else {
                throw new RuntimeException("Insufficient balance in the source account");
            }
        } else {
            throw new RuntimeException("Invalid source or destination account number");
        }
        return transaction;
    }
}
