package ch.fhnw.pizza.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.pizza.data.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByUserName(String userName);
    

}
