package com.arunkjn.calculator.core.operator;

import com.arunkjn.calculator.core.operator.impl.ClearOperator;
import com.arunkjn.calculator.core.operator.impl.DivisionOperator;
import com.arunkjn.calculator.core.operator.impl.InvalidOperator;
import com.arunkjn.calculator.core.operator.impl.MinusOperator;
import com.arunkjn.calculator.core.operator.impl.MultiplicationOperator;
import com.arunkjn.calculator.core.operator.impl.PlusOperator;
import com.arunkjn.calculator.core.operator.impl.SquareRootOperator;

public class OperatorFactory {

        public static Operator getOperator(String token) {
        OperatorType type = OperatorType.fromString(token);
        switch (type) {
            case PLUS:
                return new PlusOperator();
            case MINUS:
                return new MinusOperator();
            case DIVIDE:
                return new DivisionOperator();
            case MULTIPLY:
                return new MultiplicationOperator();
            case SQRT:
                return new SquareRootOperator();
            case CLEAR:
                return new ClearOperator();
            default:
                return new InvalidOperator();
        }
    }
}
