package com.arunkjn.calculator.core;

import com.arunkjn.calculator.core.store.CalculatorStack;
import com.arunkjn.calculator.core.store.InMemoryCalculatorStack;
import java.math.RoundingMode;
import java.util.Stack;

public class CalculatorContext {
    private final CalculatorStack stack;
    private final int storageDecimalPrecision;
    private final int displayDecimalPrecision;
    private final RoundingMode roundingMode;

    public CalculatorContext() {
        this(new InMemoryCalculatorStack(new Stack<>()));
    }

    public CalculatorContext(CalculatorStack stack) {
        this(stack, 15, 10, RoundingMode.HALF_UP);
    }

    public CalculatorContext(CalculatorStack stack, int storageDecimalPrecision, int displayDecimalPrecision, RoundingMode roundingMode) {
        if(stack == null || roundingMode == null){
            throw new NullPointerException("Arguments cannot be null");
        }
        this.stack = stack;
        this.storageDecimalPrecision = storageDecimalPrecision;
        this.displayDecimalPrecision = displayDecimalPrecision;
        this.roundingMode = roundingMode;
    }

    public CalculatorStack getStack() {
        return stack;
    }

    public int getStorageDecimalPrecision() {
        return storageDecimalPrecision;
    }

    public RoundingMode getRoundingMode() {
        return roundingMode;
    }

    public int getDisplayDecimalPrecision() {
        return displayDecimalPrecision;
    }
}
