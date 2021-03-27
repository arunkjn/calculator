package com.arunkjn.calculator.core;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * The calculator interface provides asynchronous methods to perform calculation operations
 * and retrieve the results in stack.
 *
 * In the fist phase of the project we don't really need an asynchronous interface but it has
 * been designed to accommodate the future needs of scaling the calculator to multiple clients
 * and communicating over an asynchronous api
 */
public interface Calculator {
    public CompletableFuture<Void> process(String inputString);
    public CompletableFuture<List<String>> getResult();
}
