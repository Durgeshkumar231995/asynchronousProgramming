package com.cst.asynchronous.service;



import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.cst.asynchronous.model.UserDetails;
import com.cst.asynchronous.repo.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Async
    public CompletableFuture<UserDetails> saveUser(UserDetails user) {
        return CompletableFuture.completedFuture(userRepository.save(user));
    }

   

    @Async
    public CompletableFuture<List<UserDetails>> findAllUsers() {
        return CompletableFuture.completedFuture(userRepository.findAll());
    }
    
    @Async
    public CompletableFuture<UserDetails> findUserById(Long id) {
        return CompletableFuture.completedFuture(userRepository.findById(id).orElse(null));
    }
    
    @Async
    public CompletableFuture<UserDetails> updateUser(Long id, UserDetails userDetails) {
        return findUserById(id).thenApply(user -> {
            if (user != null) {
                user.setName(userDetails.getName());
                user.setEmail(userDetails.getEmail());
                return userRepository.save(user);
            }
            return null;
        });
    }

    @Async
    public CompletableFuture<Void> deleteUser(Long id) {
        return findUserById(id).thenAccept(user -> {
            if (user != null) {
                userRepository.delete(user);
            }
        });
    }
}

