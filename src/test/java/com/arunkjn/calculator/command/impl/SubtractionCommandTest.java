package com.arunkjn.calculator.command.impl;

import com.arunkjn.calculator.core.command.impl.SubtractionCommand;
import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class SubtractionCommandTest {

    @ParameterizedTest(name = "subtraction works correctly")
    @MethodSource
    void subtractionWorksCorrectly(BigDecimal first, BigDecimal second, BigDecimal result){
        HelperUtils.commandWithTwoOperandsExecutesCorrectly(new SubtractionCommand(), first, second, result);
    }
    static Stream<Arguments> subtractionWorksCorrectly() {
        return Stream.of(
            Arguments.of(
                new BigDecimal(1),
                new BigDecimal(2),
                new BigDecimal(-1)
            ),
            Arguments.of(
                new BigDecimal("1.214"),
                new BigDecimal("2"),
                new BigDecimal("-0.786")
            ),
            Arguments.of(
                new BigDecimal("-1.214"),
                new BigDecimal("2"),
                new BigDecimal("-3.214")
            )
        );
    }
}
