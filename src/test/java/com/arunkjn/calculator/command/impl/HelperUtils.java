package com.arunkjn.calculator.command.impl;

import com.arunkjn.calculator.core.CalculatorContext;
import com.arunkjn.calculator.core.command.Command;
import com.arunkjn.calculator.core.command.Effect;
import java.math.BigDecimal;
import java.util.List;
import java.util.Stack;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;

public class HelperUtils {
    static void commandWithTwoOperandsExecutesCorrectly(Command command, BigDecimal first, BigDecimal second, BigDecimal expected){
        Assertions.assertEquals(2, command.getNumOperands());
        CalculatorContext context = new CalculatorContext();
        Stack<BigDecimal> stack = context.getStack();
        stack.push(first);
        stack.push(second);
        Effect effect = command.execute(context);
        BigDecimal result = stack.pop();
        MatcherAssert.assertThat(result, Matchers.comparesEqualTo(expected));
        Assertions.assertEquals(effect, new Effect(List.of(first, second), List.of(result)));
    }
}
