package com.arunkjn.calculator.core.command;

import com.arunkjn.calculator.core.CalculatorContext;

public interface Command {
    int getNumOperands();
    Effect operate(CalculatorContext context);
}
