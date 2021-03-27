package com.arunkjn.calculator.core.command.impl;

import com.arunkjn.calculator.core.CalculatorContext;
import com.arunkjn.calculator.core.command.Effect;
import com.arunkjn.calculator.core.command.Command;

public class InvalidCommand implements Command {
    @Override
    public int getNumOperands() {
        return 0;
    }

    @Override
    public Effect execute(CalculatorContext context) {
        throw new RuntimeException("unsupported operator");
    }
}
