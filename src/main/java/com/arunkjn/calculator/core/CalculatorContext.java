package com.arunkjn.calculator.core;

import com.arunkjn.calculator.core.command.Effect;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * A class used to represent the calculator settings and the state of calculator.
 */
public class CalculatorContext {
    /**
     * the main operating stack of calculator
     */
    private final Deque<BigDecimal> stack;
    /**
     * A stack used to support undo operations in the calculator
     */
    private final Deque<Effect> undoStack;
    /**
     * Number of decimal points to support while storing the numbers on main stack
     */
    private final int storageDecimalPrecision;
    /**
     * Number of decimal points to support when formatting the stack items for display
     */
    private final int displayDecimalPrecision;
    /**
     * Rounding mode used to round the numbers if required while storing on stack and displaying
     */
    private final RoundingMode roundingMode;

    public CalculatorContext() {
        this(new ConcurrentLinkedDeque<>(), new ConcurrentLinkedDeque<>());
    }

    public CalculatorContext(Deque<BigDecimal> stack, Deque<Effect> undoStack) {
        this(stack, undoStack, 15, 10, RoundingMode.HALF_UP);
    }

    public CalculatorContext(Deque<BigDecimal> stack, Deque<Effect> undoStack, int storageDecimalPrecision, int displayDecimalPrecision,
                             RoundingMode roundingMode) {
        if (stack == null || undoStack == null || roundingMode == null) {
            throw new NullPointerException("Arguments cannot be null");
        }
        this.stack = stack;
        this.undoStack = undoStack;
        this.storageDecimalPrecision = storageDecimalPrecision;
        this.displayDecimalPrecision = displayDecimalPrecision;
        this.roundingMode = roundingMode;
    }

    public Deque<BigDecimal> getStack() {
        return stack;
    }

    public Deque<Effect> getUndoStack() {
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
