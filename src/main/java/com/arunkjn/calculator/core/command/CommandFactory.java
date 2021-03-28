package com.arunkjn.calculator.core.command;

import com.arunkjn.calculator.core.parsing.InputToken;
import com.arunkjn.calculator.core.parsing.ParsingUtils;
import com.arunkjn.calculator.core.command.impl.ClearCommand;
import com.arunkjn.calculator.core.command.impl.DivisionCommand;
import com.arunkjn.calculator.core.command.impl.InvalidCommand;
import com.arunkjn.calculator.core.command.impl.SubtractionCommand;
import com.arunkjn.calculator.core.command.impl.MultiplicationCommand;
import com.arunkjn.calculator.core.command.impl.AdditionCommand;
import com.arunkjn.calculator.core.command.impl.PushNumberToStackCommand;
import com.arunkjn.calculator.core.command.impl.SquareRootCommand;
import com.arunkjn.calculator.core.command.impl.UndoCommand;

/**
 * This class implements a Factory design pattern to initialize an appropriate
 * command based on user input
 */
public class CommandFactory {
    public static Command getCommand(InputToken token) {
        if(ParsingUtils.isNumeric(token.getOriginalToken())) {
            return new PushNumberToStackCommand(token);
        } else {
            CommandType type = CommandType.fromString(token.getOriginalToken());
            switch (type) {
                case PLUS:
                    return new AdditionCommand();
                case MINUS:
                    return new SubtractionCommand();
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
