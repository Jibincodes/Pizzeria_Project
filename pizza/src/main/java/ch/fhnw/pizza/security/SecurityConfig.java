package ch.fhnw.pizza.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import ch.fhnw.pizza.business.service.UserDetailsServiceImpl;
//import ch.fhnw.pizza.data.domain.User;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        /*return username -> {
            ch.fhnw.pizza.data.domain.User user = userService.findByUsername(username);
            if (user != null) {
                return org.springframework.security.core.userdetails.User.builder()
                        .username(user.getUserName())
                        .password("{noop}" + user.getPassword()) // {noop} indicates no encoding
                        .roles(user.getRole().substring(5)) // change after dicussion with Charuta
                        .build();
            } else if ("myadmin".equals(username)) {
                return org.springframework.security.core.userdetails.User.withUsername("myadmin")
                    .password("{noop}password")
                    .authorities("READ", "WRITE", "ROLE_ADMIN")
                    .build();
            } else {
                throw new UsernameNotFoundException("User not found");
            }
        };*/
          return new UserDetailsServiceImpl(); 
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests( auth -> auth
                     //   .requestMatchers("/login").permitAll() // added by me today check with Devid
                        .requestMatchers("/menu").hasRole("USER") //note that the role need not be prefixed with "ROLE_"
                        .requestMatchers("/order").hasAnyRole("USER", "ADMIN")                      
                        .requestMatchers("/menu/pizza/**").hasRole("ADMIN") //note that the role need not be prefixed with "ROLE_"
                        .requestMatchers("/menu/**",
                                                    "/**", //allow access to the home page
                                                    "/swagger-ui.html", //allow access to the swagger UI
                                                    "/v3/api-docs/**", 
                                                    "/h2-console/**",
                                                    "/swagger-ui/**").permitAll() //allow access to the swagger UI
                        .anyRequest().hasAuthority("SCOPE_READ")           
                ) 
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable())) 
                .formLogin(withDefaults()) //need to include a static import for withDefaults, see the imports at the top of the file
                .httpBasic(withDefaults())
                .build(); 
    } 

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    } 

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


        
}
