package com.arunkjn.calculator.command.impl;

import com.arunkjn.calculator.core.command.impl.DivisionCommand;
import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DivisionCommandTest {
    @ParameterizedTest(name = "division works correctly")
    @MethodSource
    void divisionWorksCorrectly(BigDecimal first, BigDecimal second, BigDecimal result){
        HelperUtils.commandWithTwoOperandsExecutesCorrectly(new DivisionCommand(), first, second, result);
    }
    static Stream<Arguments> divisionWorksCorrectly() {
        return Stream.of(
            Arguments.of(
                new BigDecimal(1),
                new BigDecimal(2),
                new BigDecimal("0.5")
            ),
            Arguments.of(
                new BigDecimal("1.214"),
                new BigDecimal("2"),
                new BigDecimal("0.607")
            ),
            Arguments.of(
                new BigDecimal("-1.214"),
                new BigDecimal("2"),
                new BigDecimal("-0.607")
            ),
            Arguments.of(
                new BigDecimal("-1.214"),
                new BigDecimal("-2"),
                new BigDecimal("0.607")
            )
        );
    }

    //todo assert for exceptions thrown in corner cases
}
