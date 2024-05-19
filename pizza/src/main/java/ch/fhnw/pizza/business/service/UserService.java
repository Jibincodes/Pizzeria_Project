package ch.fhnw.pizza.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.fhnw.pizza.data.domain.User;
import ch.fhnw.pizza.data.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) throws Exception{
        if(user.getUserName() != null) {
            if (userRepository.findByUserName(user.getUserName()) == null)
                return userRepository.save(user);
            throw new Exception("User " + user.getUserName() + " already exists");
        }
        throw new Exception("Invalid user name ");

    }
    //unnecessary
    public User findByUsername(String username) {
        return userRepository.findByUserName(username);
    }
}

