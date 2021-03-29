package com.arunkjn.calculator.core.command.impl;

import com.arunkjn.calculator.core.CalculatorContext;
import com.arunkjn.calculator.core.command.Effect;
import com.arunkjn.calculator.core.command.Command;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Clears all elements from stack
 */
public class ClearCommand implements Command {
    @Override
    public int getNumOperands() {
        return 0;
    }

    @Override
    public Effect execute(CalculatorContext context) {
        final Deque<BigDecimal> stack = context.getStack();
        final List<BigDecimal> elements = new ArrayList<>(stack);
        Collections.reverse(elements);
        stack.clear();
        return new Effect(elements, List.of());
    }
}
