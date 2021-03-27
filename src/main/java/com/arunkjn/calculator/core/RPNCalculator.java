package com.arunkjn.calculator.core;

import com.arunkjn.calculator.core.exception.OperatorException;
import com.arunkjn.calculator.core.inputToken.InputToken;
import com.arunkjn.calculator.core.inputToken.impl.NumericInputToken;
import com.arunkjn.calculator.core.inputToken.impl.OperatorInputToken;
import com.arunkjn.calculator.core.operator.Operator;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

public class RPNCalculator implements Calculator{

    private final CalculatorContext context;
    private final ExecutorService executorService;

    public RPNCalculator(CalculatorContext context, ExecutorService executorService) {
        this.context = context;
        this.executorService = executorService;
    }

    @Override
    public CompletableFuture<List<String>> getResult() {
        CompletableFuture<List<String>> result = new CompletableFuture<>();
        executorService.submit(() -> {
            result.complete(
                context.getStack().getAll().stream()
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
        CompletableFuture<Void> result = new CompletableFuture<>();

        executorService.submit(() -> {
            List<InputToken> tokens = Utils.tokenize(inputString);

            for(InputToken token: tokens) {
                if(token instanceof NumericInputToken) {
                    NumericInputToken numericInputToken = (NumericInputToken) token;
                    context.getStack()
                        .push(
                            numericInputToken.getNumericValue(
                                context.getStorageDecimalPrecision(),
                                context.getRoundingMode()
                            )
                        );
                } else if(token instanceof OperatorInputToken) {
                    OperatorInputToken operatorInputToken = (OperatorInputToken) token;
                    Operator operator = operatorInputToken.getOperator();
                    if (context.getStack().size() < operator.getNumOperands()) {
                        result.completeExceptionally(new OperatorException(token.getOriginalToken(), token.getPositionInInputString(), "insufficient parameters"));
                    }
                    try {
                        operator.operate(context);
                    } catch (RuntimeException e) {
                        result.completeExceptionally(new OperatorException(token.getOriginalToken(), token.getPositionInInputString(), e.getMessage()));
                    }
                }
            }
            result.complete(null);
        });

        return result;
    }
}
