package com.arunkjn.calculator.core;

import com.arunkjn.calculator.core.command.Effect;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

public class CalculatorContext {
    private final Stack<BigDecimal> stack;
    private final Stack<Effect> undoStack;
    private final int storageDecimalPrecision;
    private final int displayDecimalPrecision;
    private final RoundingMode roundingMode;

    public CalculatorContext() {
        this(new Stack<>(), new Stack<>());
    }

    public CalculatorContext(Stack<BigDecimal> stack, Stack<Effect> undoStack) {
        this(stack, undoStack, 15, 10, RoundingMode.HALF_UP);
    }

    public CalculatorContext(Stack<BigDecimal> stack, Stack<Effect> undoStack, int storageDecimalPrecision, int displayDecimalPrecision, RoundingMode roundingMode) {
        if(stack == null || undoStack == null || roundingMode == null){
            throw new NullPointerException("Arguments cannot be null");
        }
        this.stack = stack;
        this.undoStack = undoStack;
        this.storageDecimalPrecision = storageDecimalPrecision;
        this.displayDecimalPrecision = displayDecimalPrecision;
        this.roundingMode = roundingMode;
    }

    public Stack<BigDecimal> getStack() {
        return stack;
    }

    public Stack<Effect> getUndoStack() {
        return undoStack;
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
