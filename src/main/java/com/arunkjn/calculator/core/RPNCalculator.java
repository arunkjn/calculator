package com.arunkjn.calculator.core;

import com.arunkjn.calculator.core.command.Effect;
import com.arunkjn.calculator.core.exception.OperatorException;
import com.arunkjn.calculator.core.command.Command;
import com.arunkjn.calculator.core.command.CommandFactory;
import com.arunkjn.calculator.core.parsing.InputToken;
import com.arunkjn.calculator.core.parsing.ParsingUtils;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

/**
 * An RPN implementation of calculator interface.
 * https://en.wikipedia.org/wiki/Reverse_Polish_notation
 */
public class RPNCalculator implements Calculator{

    private final CalculatorContext context;
    private final ExecutorService executorService;

    public RPNCalculator(CalculatorContext context, ExecutorService executorService) {
        if(context == null || executorService == null) {
            throw new NullPointerException("arguments cannot be null");
        }
        this.context = context;
        this.executorService = executorService;
    }

    @Override
    public synchronized CompletableFuture<List<String>> getResult() {
        final CompletableFuture<List<String>> result = new CompletableFuture<>();
        executorService.submit(() -> {
             List<String> stackElements = context.getStack().stream()
                .map(v -> v.setScale(context.getDisplayDecimalPrecision(), context.getRoundingMode()))
                .map(BigDecimal::stripTrailingZeros)
                .map(BigDecimal::toPlainString)
                .collect(Collectors.toList());
             Collections.reverse(stackElements);
            result.complete(stackElements);
        });
        return result;
    }

    /**
     * @throws OperatorException wrapped as ExecutionException
     * @param inputString - String containing numbers and operators separated byh whitespace characters
     * @return object to track the completion of this processing
     */
    @Override
    public CompletableFuture<Void> process(String inputString) {
        final CompletableFuture<Void> result = new CompletableFuture<>();

        executorService.submit(() -> {
            List<InputToken> tokens = ParsingUtils.tokenize(inputString);

            for(InputToken token: tokens) {
                Command command = CommandFactory.getCommand(token);
                if (!command.validate(context)) {
                    result.completeExceptionally(new OperatorException(token.getOriginalToken(), token.getPositionInInputString(), "insufficient parameters"));
                }
                try {
                    Effect effect = command.execute(context);
                    if(effect != null) {
                        context.getUndoStack().push(effect);
                    }
                } catch (RuntimeException e) {
                    result.completeExceptionally(new OperatorException(token.getOriginalToken(), token.getPositionInInputString(), e.getMessage()));
                }
            }
            result.complete(null);
        });

        return result;
    }
}
