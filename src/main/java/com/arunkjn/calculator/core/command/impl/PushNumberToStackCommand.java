package com.arunkjn.calculator.core.command.impl;

import com.arunkjn.calculator.core.CalculatorContext;
import com.arunkjn.calculator.core.InputToken;
import com.arunkjn.calculator.core.command.Effect;
import com.arunkjn.calculator.core.command.Command;
import java.math.BigDecimal;
import java.util.List;

public class PushNumberToStackCommand implements Command {

    private final InputToken token;

    public PushNumberToStackCommand(InputToken token) {
        this.token = token;
    }

    @Override
    public int getNumOperands() {
        return 0;
    }

    @Override
    public Effect operate(CalculatorContext context) {
        BigDecimal number = new BigDecimal(token.getOriginalToken())
            .setScale(context.getStorageDecimalPrecision(), context.getRoundingMode());
        context.getStack().push(number);
        return new Effect(List.of(), List.of(number));
    }
}
