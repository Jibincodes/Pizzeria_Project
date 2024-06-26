package ch.fhnw.pizza.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.nimbusds.jose.jwk.source.ImmutableSecret;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import javax.crypto.spec.SecretKeySpec;
import static org.springframework.security.config.Customizer.withDefaults;


import ch.fhnw.pizza.business.service.UserDetailsServiceImpl;
//import ch.fhnw.pizza.data.domain.User;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Collections;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    @Value("${jwt.key}")
    private String jwtKey; 

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
                        .requestMatchers("/myprofile").hasAnyRole("USER", "ADMIN")   
                        .requestMatchers("/order/**").authenticated()   //needed?        
                        .requestMatchers("/myprofile/**").authenticated()         
                        .requestMatchers("/menu/pizzas/**").hasRole("ADMIN") //note that the role need not be prefixed with "ROLE_"
                        .requestMatchers("/menu/**",
                                                    "/**", //allow access to the home page
                                                    "/swagger-ui.html", //allow access to the swagger UI
                                                    "/v3/api-docs/**", 
                                                    "/h2-console/**",
                                                    "/swagger-ui/**").permitAll() 
                        .requestMatchers("/api/auth/token").hasRole("USER")                          //allow access to the swagger UI
                        .anyRequest().hasAuthority("SCOPE_READ")           
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt) 
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable())) 
                .formLogin(withDefaults()) //need to include a static import for withDefaults, see the imports at the top of the file
                .httpBasic(withDefaults())
                .build(); 
    } 

    @Bean
    JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(new ImmutableSecret<>(jwtKey.getBytes()));
    }

	@Bean
    public JwtDecoder jwtDecoder() {
        byte[] bytes = jwtKey.getBytes();
        SecretKeySpec originalKey = new SecretKeySpec(bytes, 0, bytes.length,"RSA");
        return NimbusJwtDecoder.withSecretKey(originalKey).macAlgorithm(MacAlgorithm.HS512).build();
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
