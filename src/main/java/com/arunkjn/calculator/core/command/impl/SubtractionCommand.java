package com.arunkjn.calculator.core.command.impl;

import com.arunkjn.calculator.core.CalculatorContext;
import com.arunkjn.calculator.core.command.Effect;
import com.arunkjn.calculator.core.command.Command;
import java.math.BigDecimal;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

/**
 * Pops two items from stack and subtracts first from second.
 * Then pushes the result to stacl
 */
public class SubtractionCommand implements Command {
    @Override
    public int getNumOperands() {
        return 2;
    }

    @Override
    public Effect execute(CalculatorContext context) {
        final Deque<BigDecimal> stack = context.getStack();
        final BigDecimal second = stack.pop();
        final BigDecimal first = stack.pop();
        final BigDecimal result = first.subtract(second);
        stack.push(result);
        return new Effect(List.of(first, second), List.of(result));
    }
}
