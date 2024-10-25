package com.cst.asynchronous.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cst.asynchronous.model.UserDetails;
import com.cst.asynchronous.service.CustomeServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/custom/users")
public class CustomMethodUserController {
	
	@Autowired
    private CustomeServiceImpl userService;

	//1
	   @Operation(summary = "Get users by name", description = "Retrieves users with the specified name.")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "200", description = "List of users with the specified name retrieved successfully")
	    })
	@GetMapping("/name/{name}")
    public CompletableFuture<ResponseEntity<List<UserDetails>>> getByName(@PathVariable String name) {
        return userService.findByName(name)
                .thenApply(users -> ResponseEntity.ok(users));
    }

	//2
	   @Operation(summary = "Get users by email", description = "Retrieves users with the specified email.")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "200", description = "List of users with the specified email retrieved successfully")
	    })
    @GetMapping("/byEmail/{email}")
    public CompletableFuture<ResponseEntity<List<UserDetails>>> getByEmail(@PathVariable("email") String email) {
        return userService.findByEmail(email)
                .thenApply(users -> ResponseEntity.ok(users));
    }

    //3
	   @Operation(summary = "Get users containing name part", description = "Retrieves users whose names contain the specified part.")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "200", description = "List of users containing the specified part in their names retrieved successfully")
	    })
    @GetMapping("/name/contains/{namePart}")
    public CompletableFuture<ResponseEntity<List<UserDetails>>> getByNameContaining(@PathVariable String namePart) {
        return userService.findByNameContaining(namePart)
                .thenApply(users -> ResponseEntity.ok(users));
    }

    //4
	   @Operation(summary = "Get the first user by ID descending", description = "Retrieves the most recently created user.")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "200", description = "Most recent user retrieved successfully"),
	            @ApiResponse(responseCode = "404", description = "No users found")
	    })
    @GetMapping("/first")
    public CompletableFuture<ResponseEntity<UserDetails>> getFirstUser() {
        return userService.findFirstByOrderByIdDesc()
                .thenApply(user -> user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()));
    }

    //5
	   @Operation(summary = "Get top 5 users by ID ascending", description = "Retrieves the first five users created.")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "200", description = "Top 5 users retrieved successfully")
	    })
    @GetMapping("/top5")
    public CompletableFuture<ResponseEntity<List<UserDetails>>> getTop5Users() {
        return userService.findTop5ByOrderByIdAsc()
                .thenApply(users -> ResponseEntity.ok(users));
    }

    //6
	   @Operation(summary = "Get users by name or email", description = "Retrieves users matching either the specified name or email.")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "200", description = "List of users matching the criteria retrieved successfully")
	    })
    @GetMapping("/search")
    public CompletableFuture<ResponseEntity<List<UserDetails>>> getByNameOrEmail(@RequestParam String name, @RequestParam String email) {
        return userService.findByNameOrEmail(name, email)
                .thenApply(users -> ResponseEntity.ok(users));
    }
    //7

	   @Operation(summary = "Count users by email", description = "Counts the number of users with the specified email.")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "200", description = "Count of users with the specified email retrieved successfully")
	    })
    @GetMapping("/count/email/{email}")
    public CompletableFuture<ResponseEntity<Long>> countByEmail(@PathVariable String email) {
        return userService.countByEmail(email)
                .thenApply(count -> ResponseEntity.ok(count));
    }

    //8
	   @Operation(summary = "Get users by list of IDs", description = "Retrieves users whose IDs are in the specified list.")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "200", description = "List of users with the specified IDs retrieved successfully")
	    })
    @GetMapping("/ids")
    public CompletableFuture<ResponseEntity<List<UserDetails>>> getByIds(@RequestParam List<Long> ids) {
        return userService.findByIdIn(ids)
                .thenApply(users -> ResponseEntity.ok(users));
    }

    //9
	   @Operation(summary = "Get users by list of emails", description = "Retrieves users whose emails are in the specified list.")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "200", description = "List of users with the specified emails retrieved successfully")
	    })
    @GetMapping("/emails")
    public CompletableFuture<ResponseEntity<List<UserDetails>>> getByEmails(@RequestParam List<String> emails) {
        return userService.findByEmailIn(emails)
                .thenApply(users -> ResponseEntity.ok(users));
    }

    //10
	    @Operation(summary = "Delete users by email", description = "Deletes users with the specified email.")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "204", description = "User(s) deleted successfully"),
	            @ApiResponse(responseCode = "404", description = "User not found")
	    })
    @DeleteMapping("/email/{email}")
    public CompletableFuture<ResponseEntity<Void>> deleteByEmail(@PathVariable String email) {
        return userService.deleteByEmail(email)
                .thenApply(aVoid -> ResponseEntity.noContent().build());
    }
}

