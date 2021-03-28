package com.arunkjn.calculator.core.command;

import com.arunkjn.calculator.core.CalculatorContext;

/**
 * Interface to represent any command that can be performed on calculator.
 * It supports the command pattern using {@link com.arunkjn.calculator.core.command.Effect}
 *
 */
public interface Command {
    /**
     *
     * @return number of operands used by this operator which will be popped from stack
     */
    int getNumOperands();

    /**
     * Executes the logic of operator by mutating the state of calculator in {@code context}.
     * @param context - used to represent the configuration and state of calculator
     * @return the effect of performing this operation in terms of items pushed and popped from stack
     */
    Effect execute(CalculatorContext context);

    /**
     * default implementation to validate if an operation can be performed based on current state
     * @param context - object representing current state of calculator
     * @return true if operation can be performed an vice versa
     */
    default boolean validate(CalculatorContext context) {
        return context.getStack().size() >= this.getNumOperands();
    }
}
