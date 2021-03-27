package com.arunkjn.calculator.core.store;

import java.math.BigDecimal;
import java.util.List;

/**
 * This interface provides an abstraction over the storage medium of calculator stack memory.
 * In the first phase of the project, we will use a simple in memory Stack implementation,
 * and during phase 3, we can extend this to support off heap or external storage mediums such as disk, cache or database
 */
public interface CalculatorStack {
    public BigDecimal pop();
    public void push(BigDecimal value);
    public void clear();
    public int size();
    public List<BigDecimal> getAll();
}
