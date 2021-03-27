package com.arunkjn.calculator.cli;

import com.arunkjn.calculator.core.Calculator;
import com.arunkjn.calculator.core.CalculatorContext;
import com.arunkjn.calculator.core.RPNCalculator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CalculatorCli {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        final CalculatorContext context = new CalculatorContext();
        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        final Calculator calculator = new RPNCalculator(context, executorService);

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Shutting down...");
                executorService.shutdown();
            }
        }));

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Calculator started. Ctrl-C to end");
            while (true) {
                String input = scanner.nextLine();
                try {
                    calculator.process(input).get();
                } catch (ExecutionException e) {
                    System.out.println(e.getCause().getMessage());
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