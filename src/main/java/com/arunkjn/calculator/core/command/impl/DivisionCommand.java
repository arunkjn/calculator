package com.arunkjn.calculator.core.command.impl;

import com.arunkjn.calculator.core.CalculatorContext;
import com.arunkjn.calculator.core.command.Effect;
import com.arunkjn.calculator.core.command.Command;
import java.math.BigDecimal;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

/**
 * Pops two elements from stack and pushes division of second number by first
 *
 * @throws ArithmeticException if divisor is zero
 *
 */
public class DivisionCommand implements Command {
    @Override
    public int getNumOperands() {
        return 2;
    }

    @Override
    public Effect execute(CalculatorContext context) {
        final Deque<BigDecimal> stack = context.getStack();
        final BigDecimal second = stack.pop();
        final BigDecimal first = stack.pop();
        final BigDecimal result;
        try {
            result = first.divide(second, context.getStorageDecimalPrecision(), context.getRoundingMode());
        } catch (ArithmeticException e) {
            // push the operands back on stack in case of error
            stack.push(first);
            stack.push(second);
            throw e;
        }
        stack.push(result);
        return new Effect(List.of(first, second), List.of(result));
    }
}
