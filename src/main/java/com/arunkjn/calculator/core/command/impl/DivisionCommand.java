package com.arunkjn.calculator.core.command.impl;

import com.arunkjn.calculator.core.CalculatorContext;
import com.arunkjn.calculator.core.command.Effect;
import com.arunkjn.calculator.core.command.Command;
import java.math.BigDecimal;
import java.util.List;
import java.util.Stack;

public class DivisionCommand implements Command {
    @Override
    public int getNumOperands() {
        return 2;
    }

    @Override
    public Effect execute(CalculatorContext context) {
        final Stack<BigDecimal> stack = context.getStack();
        final BigDecimal second = stack.pop();
        final BigDecimal first = stack.pop();
        final BigDecimal result = first.divide(second, context.getStorageDecimalPrecision(), context.getRoundingMode());
        stack.push(result);
        return new Effect(List.of(first, second), List.of(result));
    }
}
