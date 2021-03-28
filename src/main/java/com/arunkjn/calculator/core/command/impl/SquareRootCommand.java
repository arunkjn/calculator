package com.arunkjn.calculator.core.command.impl;

import com.arunkjn.calculator.core.CalculatorContext;
import com.arunkjn.calculator.core.command.Effect;
import com.arunkjn.calculator.core.command.Command;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Stack;

/**
 * pops one item from stack and pushes its square root
 * @throws ArithmeticException if item is less than zero.
 *
 */
public class SquareRootCommand implements Command {
    @Override
    public int getNumOperands() {
        return 1;
    }

    @Override
    public Effect execute(CalculatorContext context) {
        final Stack<BigDecimal> stack = context.getStack();
        final BigDecimal operand = stack.pop();
        final BigDecimal result;
        try {
            result = operand.sqrt(MathContext.DECIMAL128)
                .setScale(context.getStorageDecimalPrecision(), context.getRoundingMode());
        } catch (ArithmeticException e) {
            // push the operand back on stack if operation fails
            stack.push(operand);
            throw e;
        }
        stack.push(result);
        return new Effect(List.of(operand), List.of(result));
    }
}
