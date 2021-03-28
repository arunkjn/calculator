package com.arunkjn.calculator.core.command.impl;

import com.arunkjn.calculator.core.CalculatorContext;
import com.arunkjn.calculator.core.command.Effect;
import com.arunkjn.calculator.core.command.Command;

/**
 *
 * Represents any invalid command that is not supported by calculator.
 *
 * @throws RuntimeException to indicate unsupported operation
 *
 */
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
