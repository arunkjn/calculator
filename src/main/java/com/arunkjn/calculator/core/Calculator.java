package com.arunkjn.calculator.core;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * The calculator interface provides asynchronous methods to perform calculation operations
 * and retrieve the results in stack.
 *
 * In the fist phase of the project we don't really need an asynchronous interface but it has
 * been designed to accommodate the future needs of running this in a web application context
 * where we can have more control over the number of threads in the application.
 */
public interface Calculator {
    /**
     * Processes a string entered by the user and returns a CompletableFuture to track the completion.
     * @param inputString - String containing numbers and operators separated byh whitespace characters
     * @return - an object used to track the completion of this processing
     */
    public CompletableFuture<Void> process(String inputString);

    /**
     * Get the list of items in calculator stack as formatted strings
     * @return - a completable future with result.
     */
    public CompletableFuture<List<String>> getResult();
}
