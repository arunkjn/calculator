package com.arunkjn.calculator.core.command;

import java.math.BigDecimal;
import java.util.List;

public class Effect {
    private final List<BigDecimal> popped;
    private final List<BigDecimal> pushed;

    public Effect(List<BigDecimal> popped, List<BigDecimal> pushed) {
        this.popped = popped;
        this.pushed = pushed;
    }

    public List<BigDecimal> getPopped() {
        return popped;
    }

    public List<BigDecimal> getPushed() {
        return pushed;
    }
}
