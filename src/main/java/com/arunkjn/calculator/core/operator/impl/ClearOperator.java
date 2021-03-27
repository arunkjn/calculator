package com.arunkjn.calculator.core.operator.impl;

import com.arunkjn.calculator.core.CalculatorContext;
import com.arunkjn.calculator.core.operator.Operator;

public class ClearOperator implements Operator {
    @Override
    public int getNumOperands() {
        return 0;
    }

    @Override
    public void operate(CalculatorContext context) {
        context.getStack().clear();
    }
}
