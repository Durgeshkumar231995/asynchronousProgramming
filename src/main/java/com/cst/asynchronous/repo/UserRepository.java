package com.cst.asynchronous.repo;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cst.asynchronous.model.UserDetails;

public interface UserRepository extends JpaRepository<UserDetails, Long> {
	
	 CompletableFuture<List<UserDetails>> findByName(String name);

	    CompletableFuture<List<UserDetails>> findByEmail(String email);

	    CompletableFuture<List<UserDetails>> findByNameContaining(String namePart);

	    CompletableFuture<Optional<UserDetails>> findFirstByOrderByIdDesc();

	    CompletableFuture<List<UserDetails>> findTop5ByOrderByIdAsc();

	    CompletableFuture<List<UserDetails>> findByNameOrEmail(String name, String email);

	    CompletableFuture<Long> countByEmail(String email);

	    CompletableFuture<List<UserDetails>> findByIdIn(List<Long> ids);

	    CompletableFuture<List<UserDetails>> findByEmailIn(List<String> emails);

	    CompletableFuture<Void> deleteByEmail(String email);

}
