package com.arunkjn.calculator.command;

import com.arunkjn.calculator.core.command.Command;
import com.arunkjn.calculator.core.command.CommandFactory;
import com.arunkjn.calculator.core.command.impl.AdditionCommand;
import com.arunkjn.calculator.core.command.impl.ClearCommand;
import com.arunkjn.calculator.core.command.impl.DivisionCommand;
import com.arunkjn.calculator.core.command.impl.InvalidCommand;
import com.arunkjn.calculator.core.command.impl.MultiplicationCommand;
import com.arunkjn.calculator.core.command.impl.PushNumberToStackCommand;
import com.arunkjn.calculator.core.command.impl.SquareRootCommand;
import com.arunkjn.calculator.core.command.impl.SubtractionCommand;
import com.arunkjn.calculator.core.command.impl.UndoCommand;
import com.arunkjn.calculator.core.parsing.InputToken;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CommandFactoryTest {

    @ParameterizedTest(name = "factory works correctly")
    @MethodSource
    void factoryWorksCorrectly(InputToken token, Class<Command> expected){
        Command actual = CommandFactory.getCommand(token);
        Assertions.assertEquals(expected, actual.getClass());
    }

    static Stream<Arguments> factoryWorksCorrectly(){
        return Stream.of(
            Arguments.of(
                new InputToken("", 1),
                InvalidCommand.class
            ),
            Arguments.of(
                new InputToken("asd", 1),
                InvalidCommand.class
            ),
            Arguments.of(
                new InputToken("123", 1),
                PushNumberToStackCommand.class
            ),
            Arguments.of(
                new InputToken("-123.23423423423", 1),
                PushNumberToStackCommand.class
            ),
            Arguments.of(
                new InputToken("+", 1),
                AdditionCommand.class
            ),
            Arguments.of(
                new InputToken("-", 1),
                SubtractionCommand.class
            ),
            Arguments.of(
                new InputToken("*", 1),
                MultiplicationCommand.class
            ),
            Arguments.of(
                new InputToken("/", 1),
                DivisionCommand.class
            ),
            Arguments.of(
                new InputToken("sqrt", 1),
                SquareRootCommand.class
            ),
            Arguments.of(
                new InputToken("clear", 1),
                ClearCommand.class
            ),
            Arguments.of(
                new InputToken("undo", 1),
                UndoCommand.class
            )
        );
    }
}
