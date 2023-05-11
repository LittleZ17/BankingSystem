package midtermProject.BankingSystem.controller.impl;

import midtermProject.BankingSystem.model.Users.AccountHolders;
import midtermProject.BankingSystem.model.Users.Admin;
import midtermProject.BankingSystem.model.Users.ThirdParty;
import midtermProject.BankingSystem.model.Users.User;
import midtermProject.BankingSystem.repository.UsersRepository.AccountHoldersRepository;
import midtermProject.BankingSystem.repository.UsersRepository.AdminRepository;
import midtermProject.BankingSystem.repository.UsersRepository.ThirdPartyRepository;
import midtermProject.BankingSystem.repository.UsersRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountHoldersRepository accountHoldersRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    /* *********** GET *********** */

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable Integer id){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) return null;
        return userOptional.get();
    }

    @GetMapping("/users/admin")
    @ResponseStatus(HttpStatus.OK)
    public List<Admin> getAdmins(){
        return adminRepository.findAll();
    }

    @GetMapping("/users/account-holders")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountHolders> getAccountHolders(){
        return accountHoldersRepository.findAll();
    }

    @GetMapping("/users/third-party")
    @ResponseStatus(HttpStatus.OK)
    public List<ThirdParty> getThirdParty(){
        return thirdPartyRepository.findAll();
    }


    /* *********** POST *********** */

    @PostMapping("/users/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin addAdmin(@RequestBody Admin admin) {
        return userRepository.save(admin);
    }

    @PostMapping("/users/account-holders")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolders addAccountHolder(@RequestBody AccountHolders accountHolders) {
        return userRepository.save(accountHolders);
    }

    @PostMapping("/users/third-party")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty thirdParty(@RequestBody ThirdParty thirdParty) {
        return thirdPartyRepository.save(thirdParty);
    }

}
