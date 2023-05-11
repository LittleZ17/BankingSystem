package midtermProject.BankingSystem.controller.interfaces;

import midtermProject.BankingSystem.model.Users.AccountHolders;
import midtermProject.BankingSystem.model.Users.Admin;
import midtermProject.BankingSystem.model.Users.ThirdParty;
import midtermProject.BankingSystem.model.Users.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IUserController {
    List<User> getUsers();
    User getUser(Integer id);
    List<Admin> getAdmins();
    List<AccountHolders> getAccountHolders();
    List<ThirdParty> getThirdParty();
    Admin addAdmin(Admin admin);
    AccountHolders addAccountHolder(AccountHolders accountHolders);
}
