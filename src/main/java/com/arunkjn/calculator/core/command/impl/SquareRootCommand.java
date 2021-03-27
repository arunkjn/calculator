package com.arunkjn.calculator.core.command.impl;

import com.arunkjn.calculator.core.CalculatorContext;
import com.arunkjn.calculator.core.command.Effect;
import com.arunkjn.calculator.core.command.Command;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Stack;

public class SquareRootCommand implements Command {
    @Override
    public int getNumOperands() {
        return 1;
    }

    @Override
    public Effect operate(CalculatorContext context) {
        final Stack<BigDecimal> stack = context.getStack();
        final BigDecimal operand = stack.pop();
        final BigDecimal result = operand.sqrt(MathContext.DECIMAL128)
            .setScale(context.getStorageDecimalPrecision(), context.getRoundingMode());
        stack.push(result);
        return new Effect(List.of(operand), List.of(result));
    }
}
