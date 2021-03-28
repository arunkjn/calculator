package com.arunkjn.calculator.cli;

import com.arunkjn.calculator.core.Calculator;
import com.arunkjn.calculator.core.CalculatorContext;
import com.arunkjn.calculator.core.RPNCalculator;
import com.arunkjn.calculator.core.exception.OperatorException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The main driver program to execute calculator-cli application
 *
 */
public class CalculatorCli {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        final CalculatorContext context = new CalculatorContext();
        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        final Calculator calculator = new RPNCalculator(context, executorService);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down...");
            executorService.shutdown();
        }));

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Calculator started. Ctrl-C to end");
            while (true) {
                String input = scanner.nextLine();
                try {
                    calculator.process(input).get();
                } catch (ExecutionException e) {
                    if(e.getCause() instanceof OperatorException) {
                        System.out.println(e.getCause().getMessage());
                    } else {
                        throw e;
                    }
                }
                try {
                    List<String> result = calculator.getResult().get();
                    System.out.println("stack: " + String.join(" ", result));
                } catch (ExecutionException e) {
                    System.out.println("Could not retreive results from calculator.");
                }
            }
        }
    }
}
