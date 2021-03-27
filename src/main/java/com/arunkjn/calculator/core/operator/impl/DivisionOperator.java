package com.arunkjn.calculator.core.operator.impl;

import com.arunkjn.calculator.core.CalculatorContext;
import com.arunkjn.calculator.core.operator.Operator;
import com.arunkjn.calculator.core.store.CalculatorStack;
import java.math.BigDecimal;
import java.util.Stack;

public class DivisionOperator implements Operator {
    @Override
    public int getNumOperands() {
        return 2;
    }

    @Override
    public void operate(CalculatorContext context) {
        CalculatorStack stack = context.getStack();
        BigDecimal second = stack.pop();
        BigDecimal first = stack.pop();
        BigDecimal result = first.divide(second, context.getStorageDecimalPrecision(), context.getRoundingMode());
        stack.push(result);
    }
}
