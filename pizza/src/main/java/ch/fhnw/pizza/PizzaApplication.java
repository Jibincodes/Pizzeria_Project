package ch.fhnw.pizza;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.pizza.business.service.MenuService;
import ch.fhnw.pizza.business.service.OrderService;
import ch.fhnw.pizza.business.service.PaymentService;
import ch.fhnw.pizza.business.service.UserService;
import ch.fhnw.pizza.data.domain.Order;
import ch.fhnw.pizza.data.domain.Payment;
import ch.fhnw.pizza.data.domain.Pizza;
import ch.fhnw.pizza.data.domain.User;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
@RestController
@Hidden // Hide this controller from the Swagger UI
public class PizzaApplication {

	@Autowired
	private MenuService menuService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private PaymentService paymentService;


	public static void main(String[] args) {
		SpringApplication.run(PizzaApplication.class, args);
	}
	

	// Use this method to initialize placeholder data without using Postman
	// If you are persisting data in a file (see application.properties), initializing data that already exists will cause an error during starting the application
	// To resolve the error, delete the file and restart the application
	@PostConstruct
	private void initPlaceholderData() throws Exception {
		Pizza pizza1 = new Pizza();
		pizza1.setPizzaName("Margherita");
		pizza1.setPizzaToppings("Tomato sauce, mozzarella, basil");
		pizza1.setPrice(10.0);
		menuService.addPizza(pizza1);

		Pizza pizza2 = new Pizza();
		pizza2.setPizzaName("Funghi");
		pizza2.setPizzaToppings("Tomato sauce, mozzarella, mushrooms");
		pizza2.setPrice(12.0);
		menuService.addPizza(pizza2);
		
		// dummy test
		User user = new User();
		user.setUserName("John");
		user.setEmail("john@email.com");
		user.setPassword("password");
		user.setPoints(0);
		user.setRole("USER");
		
		userService.addUser(user);
				
		Order order = new Order();
		order.setPizzas(Arrays.asList(pizza1, pizza2));
		order.setFinalprice(pizza1.getPrice() + pizza2.getPrice());
		order.setUser(user);
		orderService.addOrder(order);
		user.setOrders(Arrays.asList(order)); 
       // orderService.addOrder(order);

	    Payment payment = new Payment();
        payment.setOrder(order);
        payment.setCompleted(true);
		paymentService.addPayment(payment);

		User user1 = new User();
		user1.setUserName("Jithin");
		user1.setEmail("jithin@email.com");
		user1.setPassword("password");
		user1.setPoints(0);
		user1.setRole("USER");
		
		userService.addUser(user1);
      
	    //add dummy data for user as Admin as well, so we can test out all the features
   		User user2 = new User();
		user2.setUserName("Admin");
		user2.setEmail("admin@email.com");
		user2.setPassword("password");
		user2.setPoints(0);
		user2.setRole("ADMIN");
        // implement the necessary for UserDetailsServiceImpl
		// for admin I mean the role
		userService.addUser(user2);
		
		
		


		

	}

}
