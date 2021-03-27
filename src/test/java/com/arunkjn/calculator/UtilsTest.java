package com.arunkjn.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.arunkjn.calculator.core.Utils;
import com.arunkjn.calculator.core.inputToken.InputToken;
import com.arunkjn.calculator.core.inputToken.impl.NumericInputToken;
import com.arunkjn.calculator.core.inputToken.impl.OperatorInputToken;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class UtilsTest {

    @ParameterizedTest(name = "Tokenizer works correctly")
    @MethodSource
    void tokenizer(String inputString, List<InputToken> expectedTokens){
        assertEquals(expectedTokens, Utils.tokenize(inputString));
    }

    static Stream<Arguments> tokenizer(){
        return Stream.of(
            Arguments.of(
                "asd asd asd",
                List.of(
                    new OperatorInputToken("asd", 0),
                    new OperatorInputToken("asd",4),
                    new OperatorInputToken("asd",8)
                )
            ),
            Arguments.of(
                "asd   asd asd",
                List.of(
                    new OperatorInputToken("asd", 0),
                    new OperatorInputToken("asd",6),
                    new OperatorInputToken("asd",10)
                )
            ),
            Arguments.of(
                "asd \tasd asd",
                List.of(
                    new OperatorInputToken("asd", 0),
                    new OperatorInputToken("asd",5),
                    new OperatorInputToken("asd",9)
                )
            ),
            Arguments.of(
                "  asd asd asd",
                List.of(
                    new OperatorInputToken("asd", 2),
                    new OperatorInputToken("asd",6),
                    new OperatorInputToken("asd",10)
                )
            ),
            Arguments.of(
                "asd asd asd  ",
                List.of(
                    new OperatorInputToken("asd", 0),
                    new OperatorInputToken("asd",4),
                    new OperatorInputToken("asd",8)
                )
            ),
            Arguments.of(
                "  asd asd asd  ",
                List.of(
                    new OperatorInputToken("asd", 2),
                    new OperatorInputToken("asd",6),
                    new OperatorInputToken("asd",10)
                )
            ),
            Arguments.of(
                "2 3 4 5 + - *",
                List.of(
                    new NumericInputToken("2", 0),
                    new NumericInputToken("3", 2),
                    new NumericInputToken("4", 4),
                    new NumericInputToken("5", 6),
                    new OperatorInputToken("+", 8),
                    new OperatorInputToken("-",10),
                    new OperatorInputToken("*",12)
                )
            )
        );
    }

    @Test
    void tokenizerExceptionTest(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Utils.tokenize(null);
        });
    }

    @ParameterizedTest(name = "Number detection works")
    @MethodSource
    void isNumeric(String input, boolean isNumeric){
        assertEquals(isNumeric, Utils.isNumeric(input));
    }

    static Stream<Arguments> isNumeric(){
        return Stream.of(
            Arguments.of("-1", true),
            Arguments.of("-133.32234234", true),
            Arguments.of("134.3242342342342334242342342342342342342342", true),
            Arguments.of("2F", false),
            Arguments.of("10e4", false),
            Arguments.of("0xFF", false),
            Arguments.of("2.99e+8", false),
            Arguments.of("My name is : real slim shady", false),
            Arguments.of("", false),
            Arguments.of("null", false),
            Arguments.of(null, false)
        );
    }
}
