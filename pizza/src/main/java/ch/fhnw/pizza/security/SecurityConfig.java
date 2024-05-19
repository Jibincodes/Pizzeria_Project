package ch.fhnw.pizza.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import ch.fhnw.pizza.business.service.UserService;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return username -> {
            if ("myuser".equals(username)) {
                return User.withUsername("myuser")
                    .password("{noop}password")
                    .authorities("READ", "ROLE_USER")
                    .build();
            } else if ("myadmin".equals(username)) {
                return User.withUsername("myadmin")
                    .password("{noop}password")
                    .authorities("READ", "WRITE", "ROLE_ADMIN")
                    .build();
            } else {// check in school, could be wrong
                ch.fhnw.pizza.data.domain.User user = userService.findByUsername(username);
                if (user != null) {
                    // Retrieve user details from the database and return accordingly
                    return new org.springframework.security.core.userdetails.User(
                            user.getUserName(),
                            user.getPassword(),
                            Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                    );
                } else {
                    throw new UsernameNotFoundException("User not found");
                }
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests( auth -> auth
                     //   .requestMatchers("/login").permitAll() // added by me today check with Devid
                        .requestMatchers("/menu").hasRole("USER") //note that the role need not be prefixed with "ROLE_"
                        .requestMatchers("/order").hasRole("USER")
                        .requestMatchers("/order").hasRole("ADMIN")
                        .requestMatchers("/menu/pizza/**").hasRole("ADMIN") //note that the role need not be prefixed with "ROLE_"
                        .requestMatchers("/menu/**",
                                                    "/**", //allow access to the home page
                                                    "/swagger-ui.html", //allow access to the swagger UI
                                                    "/v3/api-docs/**",  //allow access to the swagger API documentation
                                                    "/swagger-ui/**").permitAll() //allow access to the swagger UI
                        .anyRequest().hasAuthority("SCOPE_READ")           
                )       
                .formLogin(withDefaults()) //need to include a static import for withDefaults, see the imports at the top of the file
                .httpBasic(withDefaults())
                .build(); 
    } 



        
}
