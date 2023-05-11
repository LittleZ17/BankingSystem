package midtermProject.BankingSystem.service.impl;


import midtermProject.BankingSystem.model.Users.User;
import midtermProject.BankingSystem.repository.UsersRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ServiceUser {
    @Autowired
    UserRepository userRepository;

    public User getUserById(Integer number) {
        Optional<User> userOptional = userRepository.findById(number);
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"UserID is not found") ;
        }
        return userOptional.get();
    }
}
