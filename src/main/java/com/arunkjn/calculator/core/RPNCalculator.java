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
import java.util.stream.Collectors;

/**
 * An RPN implementation of calculator interface. This is a thread safe implementation
 * that can be used safely in a multi threaded environment such as a webapp.
 * https://en.wikipedia.org/wiki/Reverse_Polish_notation
 */
public class RPNCalculator implements Calculator{

    private final CalculatorContext context;

    public RPNCalculator(CalculatorContext context) {
        if(context == null) {
            throw new NullPointerException("arguments cannot be null");
        }
        this.context = context;
    }

    /**
     *
     * This method uses synchronized as Iteration on ConcurrentLinkedDeque is weakly consistent
     * in multi threaded environments
     * @return List of formatted values in calculator stack in natural order
     */
    @Override
    public synchronized List<String> getResult() {
        List<String> stackElements = context.getStack().stream()
            .map(v -> v.setScale(context.getDisplayDecimalPrecision(), context.getRoundingMode()))
            .map(BigDecimal::stripTrailingZeros)
            .map(BigDecimal::toPlainString)
            .collect(Collectors.toList());
        Collections.reverse(stackElements);
        return stackElements;
    }

    /**
     *
     * Processes a given user input on the calculator state. This method is syncronized to provide
     * thread safety in a multi threaded environment.
     * @throws OperatorException wrapped as ExecutionException
     * @param inputString - String containing numbers and operators separated byh whitespace characters
     *
     */
    @Override
    public synchronized void process(String inputString) {
        List<InputToken> tokens = ParsingUtils.tokenize(inputString);

        for(InputToken token: tokens) {
            Command command = CommandFactory.getCommand(token);
            if (!command.validate(context)) {
                throw new OperatorException(token.getOriginalToken(), token.getPositionInInputString(), "insufficient parameters");
            }
            try {
                Effect effect = command.execute(context);
                if(effect != null) {
                    context.getUndoStack().push(effect);
                }
            } catch (RuntimeException e) {
                throw new OperatorException(token.getOriginalToken(), token.getPositionInInputString(), e.getMessage());
            }
        }
    }
}
