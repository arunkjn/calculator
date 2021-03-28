package com.arunkjn.calculator.core;

import com.arunkjn.calculator.core.command.Effect;
import com.arunkjn.calculator.core.exception.OperatorException;
import com.arunkjn.calculator.core.command.Command;
import com.arunkjn.calculator.core.command.CommandFactory;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

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
    public CompletableFuture<List<String>> getResult() {
        final CompletableFuture<List<String>> result = new CompletableFuture<>();
        executorService.submit(() -> {
            result.complete(
                context.getStack().stream()
                    .map(v -> v.setScale(context.getDisplayDecimalPrecision(), context.getRoundingMode()))
                    .map(BigDecimal::stripTrailingZeros)
                    .map(BigDecimal::toPlainString)
                    .collect(Collectors.toList())
            );
        });
        return result;
    }

    @Override
    public CompletableFuture<Void> process(String inputString) {
        final CompletableFuture<Void> result = new CompletableFuture<>();

        executorService.submit(() -> {
            List<InputToken> tokens = Utils.tokenize(inputString);

            for(InputToken token: tokens) {
                Command command = CommandFactory.getOperator(token);
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
