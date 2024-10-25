package com.cst.asynchronous.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cst.asynchronous.service.AsyncService;

@RestController
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @GetMapping("/async-task")
    public CompletableFuture<ResponseEntity<String>> executeAsyncTask() {
        return asyncService.longRunningTask()
                .thenApply(ResponseEntity::ok);
    }
}
