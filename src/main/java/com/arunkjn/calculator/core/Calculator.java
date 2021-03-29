package com.arunkjn.calculator.core;

import java.util.List;

/**
 * The calculator interface provides asynchronous methods to perform calculation operations
 * and retrieve the results in stack.
 *
 */
public interface Calculator {
    /**
     * Processes a string entered by the user and returns a CompletableFuture to track the completion.
     * @param inputString - String containing numbers and operators separated byh whitespace characters
     *
     */
    void process(String inputString);

    /**
     * Get the list of items in calculator stack as formatted strings
     * @return - List of values in stack in natural order
     */
    List<String> getResult();
}
