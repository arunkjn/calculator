package com.arunkjn.calculator.core.command;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * An object used to represent the 'effect' of an operator based on the items
 * pushed and popped from the stack
 */
public class Effect {
    private final List<BigDecimal> popped;
    private final List<BigDecimal> pushed;

    public Effect(List<BigDecimal> popped, List<BigDecimal> pushed) {
        if(popped == null || pushed == null) {
            throw new NullPointerException("arguments cannot be null");
        }
        this.popped = popped;
        this.pushed = pushed;
    }

    public List<BigDecimal> getPopped() {
        return popped;
    }
    public List<BigDecimal> getPushed() {
        return pushed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Effect effect = (Effect) o;
        return popped.equals(effect.popped) && pushed.equals(effect.pushed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(popped, pushed);
    }
}
