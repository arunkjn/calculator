package com.arunkjn.calculator.core.command.impl;

import com.arunkjn.calculator.core.CalculatorContext;
import com.arunkjn.calculator.core.command.Effect;
import com.arunkjn.calculator.core.command.Command;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ClearCommand implements Command {
    @Override
    public int getNumOperands() {
        return 0;
    }

    @Override
    public Effect operate(CalculatorContext context) {
        final Stack<BigDecimal> stack = context.getStack();
        final List<BigDecimal> elements = new ArrayList<>(stack.size());
        while (!stack.empty()){
            elements.add(stack.pop());
        }
        return new Effect(elements, List.of());
    }
}
