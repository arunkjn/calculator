package com.arunkjn.calculator.command.impl;

import com.arunkjn.calculator.core.command.impl.AdditionCommand;
import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class AdditionCommandTest {

    @ParameterizedTest(name = "addition works correctly")
    @MethodSource
    void additionWorksCorrectly(BigDecimal first, BigDecimal second, BigDecimal result){
        HelperUtils.commandWithTwoOperandsExecutesCorrectly(new AdditionCommand(), first, second, result);
    }

    static Stream<Arguments> additionWorksCorrectly() {
        return Stream.of(
            Arguments.of(
                new BigDecimal(1),
                new BigDecimal(2),
                new BigDecimal(3)
            ),
            Arguments.of(
                new BigDecimal("1.214"),
                new BigDecimal("2"),
                new BigDecimal("3.214")
            ),
            Arguments.of(
                new BigDecimal("-1.214"),
                new BigDecimal("2"),
                new BigDecimal("0.786")
            )
        );
    }
}
