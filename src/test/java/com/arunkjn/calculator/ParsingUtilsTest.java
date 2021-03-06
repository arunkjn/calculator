package com.arunkjn.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.arunkjn.calculator.core.parsing.InputToken;
import com.arunkjn.calculator.core.parsing.ParsingUtils;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ParsingUtilsTest {

    @ParameterizedTest(name = "Tokenizer works correctly")
    @MethodSource
    void tokenizer(String inputString, List<InputToken> expectedTokens){
        assertEquals(expectedTokens, ParsingUtils.tokenize(inputString));
    }

    static Stream<Arguments> tokenizer(){
        return Stream.of(
            Arguments.of(
                "asd asd asd",
                List.of(
                    new InputToken("asd", 1),
                    new InputToken("asd",5),
                    new InputToken("asd",9)
                )
            ),
            Arguments.of(
                "asd   asd asd",
                List.of(
                    new InputToken("asd", 1),
                    new InputToken("asd",7),
                    new InputToken("asd",11)
                )
            ),
            Arguments.of(
                "asd \tasd asd",
                List.of(
                    new InputToken("asd", 1),
                    new InputToken("asd",6),
                    new InputToken("asd",10)
                )
            ),
            Arguments.of(
                "  asd asd asd",
                List.of(
                    new InputToken("asd", 3),
                    new InputToken("asd",7),
                    new InputToken("asd",11)
                )
            ),
            Arguments.of(
                "asd asd asd  ",
                List.of(
                    new InputToken("asd", 1),
                    new InputToken("asd",5),
                    new InputToken("asd",9)
                )
            ),
            Arguments.of(
                "  asd asd asd  ",
                List.of(
                    new InputToken("asd", 3),
                    new InputToken("asd",7),
                    new InputToken("asd",11)
                )
            ),
            Arguments.of(
                "2 3 4 5 + - *",
                List.of(
                    new InputToken("2", 1),
                    new InputToken("3", 3),
                    new InputToken("4", 5),
                    new InputToken("5", 7),
                    new InputToken("+", 9),
                    new InputToken("-",11),
                    new InputToken("*",13)
                )
            )
        );
    }

    @Test
    void tokenizerExceptionTest(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ParsingUtils.tokenize(null);
        });
    }

    @ParameterizedTest(name = "Number detection works")
    @MethodSource
    void isNumeric(String input, boolean isNumeric){
        assertEquals(isNumeric, ParsingUtils.isNumeric(input));
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
