package com.cst.asynchronous.service;



import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    @Async
    public CompletableFuture<String> longRunningTask() {
        try {
            // Simulating a long-running task
            Thread.sleep(5000); // Sleep for 5 seconds
          int a=  10/0;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return CompletableFuture.completedFuture("Task interrupted");
        }
        return CompletableFuture.completedFuture("Task completed");
    }
}

