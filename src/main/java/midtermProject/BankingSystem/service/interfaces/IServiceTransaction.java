package midtermProject.BankingSystem.service.interfaces;

import midtermProject.BankingSystem.model.Operations.Transaction;

public interface IServiceTransaction {
    Transaction createTransaction(Transaction transaction);

}
