package com.arunkjn.calculator.command.impl;

import com.arunkjn.calculator.core.command.impl.MultiplicationCommand;
import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MultiplicationCommandTest {

    @ParameterizedTest(name = "multiplication works correctly")
    @MethodSource
    void multiplicationWorksCorrectly(BigDecimal first, BigDecimal second, BigDecimal result){
        HelperUtils.commandWithTwoOperandsExecutesCorrectly(new MultiplicationCommand(), first, second, result);
    }
    static Stream<Arguments> multiplicationWorksCorrectly() {
        return Stream.of(
            Arguments.of(
                new BigDecimal(1),
                new BigDecimal(2),
                new BigDecimal(2)
            ),
            Arguments.of(
                new BigDecimal("1.214"),
                new BigDecimal("2"),
                new BigDecimal("2.428")
            ),
            Arguments.of(
                new BigDecimal("-1.214"),
                new BigDecimal("2"),
                new BigDecimal("-2.428")
            ),
            Arguments.of(
                new BigDecimal("-1.214"),
                new BigDecimal("-2"),
                new BigDecimal("2.428")
            )
        );
    }
}
