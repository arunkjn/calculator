package com.arunkjn.calculator.cli;

import com.arunkjn.calculator.core.Calculator;
import com.arunkjn.calculator.core.CalculatorContext;
import com.arunkjn.calculator.core.RPNCalculator;
import com.arunkjn.calculator.core.exception.OperatorException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * The main driver program to execute calculator-cli application
 *
 */
public class CalculatorCli {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        final CalculatorContext context = new CalculatorContext();
        final Calculator calculator = new RPNCalculator(context);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down...");
        }));

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Calculator started. Ctrl-C to end");
            while (true) {
                String input = scanner.nextLine();
                try {
                    calculator.process(input);
                } catch (OperatorException e) {
                    System.out.println(e.getMessage());
                }
                List<String> result = calculator.getResult();
                System.out.println("stack: " + String.join(" ", result));
            }
        }
    }
}
