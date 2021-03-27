package com.arunkjn.calculator.core.inputToken.impl;

import com.arunkjn.calculator.core.inputToken.AbstractInputToken;
import com.arunkjn.calculator.core.operator.OperatorFactory;
import com.arunkjn.calculator.core.operator.Operator;

public class OperatorInputToken extends AbstractInputToken {
    private final Operator operator;

    public OperatorInputToken(String token, int position) {
        super(token, position);
        this.operator = OperatorFactory.getOperator(token);
    }

    public Operator getOperator() {
        return operator;
    }
}
