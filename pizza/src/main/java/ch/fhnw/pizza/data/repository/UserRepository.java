package ch.fhnw.pizza.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ch.fhnw.pizza.data.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
   // @Query("SELECT u FROM User u WHERE u.userName = :userName")
   // User findByUserName(@Param("userName") String userName);  
   User findByUserName(String userName);
}
