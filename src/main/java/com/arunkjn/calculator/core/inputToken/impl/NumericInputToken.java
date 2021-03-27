package com.arunkjn.calculator.core.inputToken.impl;

import com.arunkjn.calculator.core.inputToken.AbstractInputToken;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumericInputToken extends AbstractInputToken {

    public NumericInputToken(String input, int position) {
        super(input, position);
    }

    public BigDecimal getNumericValue(int decimalPrecision, RoundingMode roundingMode){
        return new BigDecimal(getOriginalToken()).setScale(decimalPrecision, roundingMode);
    }
}
