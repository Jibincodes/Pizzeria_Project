package ch.fhnw.pizza.data.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "app_user") // Changed table name as user is a reserved keyword in SQL
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "userName")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
    
    @Column(name = "points")
    private double points;

    @Column(name = "role")
    private String role;

    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    //discuss this 
    @JsonManagedReference
    private List<Order> orders;

    public User() {}
        public User (User user)
        {
          this.userName = user.getUserName();
          this.email = user.getEmail();
          this.password = user.getPassword();
          this.points = user.getPoints();
          this.orders = user.getOrders();
          this.role = user.getRole();
        }
        
    public Long getId() {
            return id;
        }
    public void setId(Long id) {
            this.id = id;
        }
    public String getUserName() {
        return userName;
        }
    public void setUserName(String userName) {
        this.userName = userName;
        }
    public String getEmail() {
        return email;
        }
    public void setEmail(String email) {
        this.email = email;
        }
    public String getPassword() {
        return password;
        }
    public void setPassword(String password) {
        this.password = password;
        }
    public double getPoints() {
        return points;
        }
    public void setPoints(double points) {
        this.points = points;
        }
    public String getRole() {
            return role;
        }
    public void setRole(String role) {
            this.role = role;
        }    
    // create order class next and it should be fixed
    public List<Order> getOrders() {
        return orders;
        }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
        }

    


}
