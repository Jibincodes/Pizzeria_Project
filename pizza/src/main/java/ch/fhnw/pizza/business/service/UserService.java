package ch.fhnw.pizza.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ch.fhnw.pizza.data.domain.User;
import ch.fhnw.pizza.data.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User addUser(User user) throws Exception{
        if(user.getUserName() != null) {
            if (userRepository.findByUserName(user.getUserName()) == null){
                //added extra 
               // user.setRole("ROLE_USER");
                //might not be needed the above line
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                return userRepository.save(user);
            }

            throw new Exception("User " + user.getUserName() + " already exists");
        }
        throw new Exception("Invalid user name ");

    }
    //unnecessary -- needed
    public User findByUsername(String username) {
        return userRepository.findByUserName(username);
    }
}

