package com.arunkjn.calculator.core.operator;

import com.arunkjn.calculator.core.CalculatorContext;

public interface Operator {
    int getNumOperands();
    void operate(CalculatorContext context);
}
