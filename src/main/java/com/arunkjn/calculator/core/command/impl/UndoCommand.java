package com.arunkjn.calculator.core.command.impl;

import com.arunkjn.calculator.core.CalculatorContext;
import com.arunkjn.calculator.core.command.Effect;
import com.arunkjn.calculator.core.command.Command;
import java.math.BigDecimal;
import java.util.List;

public class UndoCommand implements Command {
    @Override
    public int getNumOperands() {
        return 0;
    }

    @Override
    public Effect operate(CalculatorContext context) {
        if(context.getUndoStack().isEmpty()) {
            return null;
        }
        Effect effect = context.getUndoStack().pop();
        int numPushed = effect.getPushed().size();
        List<BigDecimal> poppedElements = effect.getPopped();

        while (numPushed-- > 0) {
            context.getStack().pop();
        }
        poppedElements.forEach(element -> context.getStack().push(element));
        return null;
    }
}
