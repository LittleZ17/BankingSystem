package midtermProject.BankingSystem.controller.impl;

import midtermProject.BankingSystem.controller.interfaces.ITransactionController;
import midtermProject.BankingSystem.model.Operations.Transaction;
import midtermProject.BankingSystem.repository.OperationRepository.TransactionRepository;
import midtermProject.BankingSystem.service.impl.ServiceTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TransactionController implements ITransactionController {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ServiceTransaction serviceTransaction;

    @GetMapping("/transactions")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @GetMapping("/transaction/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Transaction> getTransaction(@PathVariable("id") Integer id) {
        return transactionRepository.findById(id);
    }

    @PostMapping("transactions/newTransaction")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction createTransaction(@RequestBody Transaction transaction) {
     return serviceTransaction.createTransaction(transaction);
    }

    @PostMapping("transactions/thirdPartyT")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction createThirdPartyTransaction(@RequestBody Transaction transaction){
        return serviceTransaction.createTransaction(transaction);
    }

}


