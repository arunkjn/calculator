package com.arunkjn.calculator.core.store;

import java.math.BigDecimal;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class InMemoryCalculatorStack implements CalculatorStack{
    private final Stack<BigDecimal> stack;

    public InMemoryCalculatorStack(Stack<BigDecimal> stack) {
        this.stack = stack;
    }

    @Override
    public BigDecimal pop() {
        return stack.pop();
    }

    @Override
    public void push(BigDecimal value) {
        stack.push(value);
    }

    @Override
    public void clear() {
        stack.clear();
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public List<BigDecimal> getAll() {
        return stack.stream().map(v -> v).collect(Collectors.toList());
    }
}
