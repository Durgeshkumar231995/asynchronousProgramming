package com.cst.asynchronous.dbconfig;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cst.asynchronous.model.UserDetails;
import com.cst.asynchronous.repo.UserRepository;

@Configuration
public class DatabaseInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            userRepository.save(new UserDetails(null,"Alice", "alice@example.com"));
            userRepository.save(new UserDetails(null,"Bob", "bob@example.com"));
            userRepository.save(new UserDetails(null,"Durgesh", "Durgesh@example.com"));
            userRepository.save(new UserDetails(null,"Dk", "Dk@example.com"));
        };
    }
}
