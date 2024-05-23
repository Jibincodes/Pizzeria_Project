package ch.fhnw.pizza.business.service;

import org.aspectj.weaver.loadtime.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

//import ch.fhnw.pizza.data.domain.User;
import ch.fhnw.pizza.data.repository.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
        ch.fhnw.pizza.data.domain.User dbUser = userRepository.findByUserName(username);
    
    if (dbUser == null) {
        throw new UsernameNotFoundException(username);
    }
    
    UserDetails user = User.builder()
                            .username(dbUser.getUserName())
                            .password(dbUser.getPassword())
                            .authorities("READ","ROLE_" + dbUser.getRole())
                            .build();

    return user;
}
    

}
