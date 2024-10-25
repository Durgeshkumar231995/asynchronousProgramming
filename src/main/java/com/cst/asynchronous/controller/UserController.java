package com.cst.asynchronous.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cst.asynchronous.model.UserDetails;
import com.cst.asynchronous.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
/**
 * UserController handles all user-related operations.
 * This includes creating, retrieving, updating, and deleting users,
 * as well as various custom queries for user data.
 */
@CrossOrigin("*")
@RestController
//@Api(value = "User Management System", tags = "User Management")
@Tag(name = "User Management System", description = "Operations related to user management, including creating, updating, retrieving, and deleting users.")
public class UserController {

	
	//http://localhost:9002/asynchronous/cts/swagger-ui/index.html
    @Autowired
    private UserService userService;
    
    @Operation(summary = "Create a new user", description = "Creates a user with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    
    @PostMapping
    public CompletableFuture<ResponseEntity<UserDetails>> createUser(@RequestBody UserDetails user) {
        return userService.saveUser(user)
                .thenApply(savedUser -> ResponseEntity.ok(savedUser));
    }

    @Operation(summary = "Get all users", description = "Retrieves a list of all users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of users retrieved successfully")
    })
    @GetMapping("/users")
    public CompletableFuture<ResponseEntity<List<UserDetails>>> fetchUsers() {
        return userService.findAllUsers()
                .thenApply(ResponseEntity::ok);
        //   .thenApply(users -> ResponseEntity.ok(users));
    }
    
  

    
    @Operation(summary = "Get user by ID", description = "Retrieves a user by their unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found", 
                         content = @Content(mediaType = "application/json", 
                         schema = @Schema(implementation = User.class, 
                         example = "{\"id\": 1, \"name\": \"John Doe\", \"email\": \"john.doe@example.com\"}"))),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<UserDetails>> getUserById(@PathVariable Long id) {
        return userService.findUserById(id)
                .thenApply(user -> user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build());
    }
    
    
    @Operation(summary = "Update a user", description = "Updates the details of an existing user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<UserDetails>> updateUser(@PathVariable Long id, @RequestBody UserDetails userDetails) {
        return userService.updateUser(id, userDetails)
                .thenApply(updatedUser -> updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a user", description = "Deletes a user by their unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id)
                .thenApply(aVoid -> ResponseEntity.noContent().build());
    }
}

