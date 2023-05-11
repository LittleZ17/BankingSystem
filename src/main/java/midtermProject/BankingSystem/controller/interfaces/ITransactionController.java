package midtermProject.BankingSystem.controller.interfaces;

import midtermProject.BankingSystem.model.Operations.Transaction;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface ITransactionController {
    List<Transaction> getAllTransactions();
    public Optional<Transaction> getTransaction(Integer id);
    public Transaction createTransaction(Transaction transaction);

    }
