package com.arunkjn.calculator.core.command;

import com.arunkjn.calculator.core.InputToken;
import com.arunkjn.calculator.core.Utils;
import com.arunkjn.calculator.core.command.impl.ClearCommand;
import com.arunkjn.calculator.core.command.impl.DivisionCommand;
import com.arunkjn.calculator.core.command.impl.InvalidCommand;
import com.arunkjn.calculator.core.command.impl.MinusCommand;
import com.arunkjn.calculator.core.command.impl.MultiplicationCommand;
import com.arunkjn.calculator.core.command.impl.PlusCommand;
import com.arunkjn.calculator.core.command.impl.PushNumberToStackCommand;
import com.arunkjn.calculator.core.command.impl.SquareRootCommand;
import com.arunkjn.calculator.core.command.impl.UndoCommand;

public class CommandFactory {
    public static Command getOperator(InputToken token) {
        if(Utils.isNumeric(token.getOriginalToken())) {
            return new PushNumberToStackCommand(token);
        } else {
            CommandType type = CommandType.fromString(token.getOriginalToken());
            switch (type) {
                case PLUS:
                    return new PlusCommand();
                case MINUS:
                    return new MinusCommand();
                case DIVIDE:
                    return new DivisionCommand();
                case MULTIPLY:
                    return new MultiplicationCommand();
                case SQRT:
                    return new SquareRootCommand();
                case CLEAR:
                    return new ClearCommand();
                case UNDO:
                    return new UndoCommand();
                default:
                    return new InvalidCommand();
            }
        }
    }
}
