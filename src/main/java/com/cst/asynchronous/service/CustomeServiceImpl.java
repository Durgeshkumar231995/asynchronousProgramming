package com.cst.asynchronous.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.cst.asynchronous.model.UserDetails;
import com.cst.asynchronous.repo.UserRepository;

@Service
public class CustomeServiceImpl {

	@Autowired
	private UserRepository userRepository;

	@Async
	public CompletableFuture<List<UserDetails>> findByName(String name) {
		return userRepository.findByName(name);
	}

	@Async
	public CompletableFuture<List<UserDetails>> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Async
	public CompletableFuture<List<UserDetails>> findByNameContaining(String namePart) {
		return userRepository.findByNameContaining(namePart);
	}

	@Async
	public CompletableFuture<Optional<UserDetails>> findFirstByOrderByIdDesc() {
		return userRepository.findFirstByOrderByIdDesc();
	}

	@Async
	public CompletableFuture<List<UserDetails>> findTop5ByOrderByIdAsc() {
		return userRepository.findTop5ByOrderByIdAsc();
	}

	@Async
	public CompletableFuture<List<UserDetails>> findByNameOrEmail(String name, String email) {
		return userRepository.findByNameOrEmail(name, email);
	}

	@Async
	public CompletableFuture<Long> countByEmail(String email) {
		return userRepository.countByEmail(email);
	}

	@Async
	public CompletableFuture<List<UserDetails>> findByIdIn(List<Long> ids) {
		return userRepository.findByIdIn(ids);
	}

	@Async
	public CompletableFuture<List<UserDetails>> findByEmailIn(List<String> emails) {
		return userRepository.findByEmailIn(emails);
	}

	@Async
	public CompletableFuture<Void> deleteByEmail(String email) {
		return userRepository.deleteByEmail(email);
	}
}
