package com.arunkjn.calculator.core.operator.impl;

import com.arunkjn.calculator.core.CalculatorContext;
import com.arunkjn.calculator.core.operator.Operator;
import com.arunkjn.calculator.core.store.CalculatorStack;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Stack;

public class SquareRootOperator implements Operator {
    @Override
    public int getNumOperands() {
        return 1;
    }

    @Override
    public void operate(CalculatorContext context) {
        CalculatorStack stack = context.getStack();
        BigDecimal operand = stack.pop();
        BigDecimal result = operand.sqrt(MathContext.DECIMAL128)
            .setScale(context.getStorageDecimalPrecision(), context.getRoundingMode());
        stack.push(result);
    }
}
