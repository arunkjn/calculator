package com.arunkjn.calculator;

import com.arunkjn.calculator.core.Calculator;
import com.arunkjn.calculator.core.CalculatorContext;
import com.arunkjn.calculator.core.RPNCalculator;
import com.arunkjn.calculator.core.exception.OperatorException;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RPNCalculatorTest {

    @ParameterizedTest
    @MethodSource
    void calculatorWorksCorrectly(List<String> inputs, List<List<String>> outputs, List<String> exceptionMessages) throws InterruptedException {
        if(inputs.size() != outputs.size() || inputs.size() != exceptionMessages.size()) {
            throw new IllegalArgumentException("all input lists should be same length");
        }
        CalculatorContext context = new CalculatorContext();
        Calculator calculator = new RPNCalculator(context);
        int i = 0;
        for (String input: inputs) {
            try {
                calculator.process(input);
                if (!exceptionMessages.get(i).equals("")) {
                    Assertions.fail("Required exception was not thrown at index " + i);
                }
            } catch (OperatorException e) {
                String expected = exceptionMessages.get(i);
                if(expected.equals("")) {
                    Assertions.fail(e.getCause().getMessage());
                }
                Assertions.assertEquals(expected, e.getMessage());
            }
            List<String> result = calculator.getResult();
            Assertions.assertEquals(outputs.get(i), result);
            i++;
        }
    }

    static Stream<Arguments> calculatorWorksCorrectly(){
        return Stream.of(
            Arguments.of(
                List.of("2 3 4 5 + -"),
                List.of(
                    List.of("2", "-6")
                ),
                List.of("")
            ),
            Arguments.of(
                List.of("5 2"),
                List.of(
                    List.of("5", "2")
                ),
                List.of("")
            ),
            Arguments.of(
                List.of("2 sqrt", "clear 9 sqrt"),
                List.of(
                    List.of("1.4142135624"),
                    List.of("3")
                ),
                List.of("", "")
            ),
            Arguments.of(
                List.of("5 2 -", "3 -", "clear"),
                List.of(
                    List.of("3"),
                    List.of("0"),
                    List.of()
                ),
                List.of("", "", "")
            ),
            Arguments.of(
                List.of("5 4 3 2", "undo undo *", "5 *", "undo"),
                List.of(
                    List.of("5", "4", "3", "2"),
                    List.of("20"),
                    List.of("100"),
                    List.of("20", "5")
                ),
                List.of("", "", "", "")
            ),
            Arguments.of(
                List.of("7 12 2 /", "*", "4 /"),
                List.of(
                    List.of("7", "6"),
                    List.of("42"),
                    List.of("10.5")
                ),
                List.of("", "", "")
            ),
            Arguments.of(
                List.of("1 2 3 4 5", "* * * *"),
                List.of(
                    List.of("1", "2", "3", "4", "5"),
                    List.of("120")
                ),
                List.of("", "")
            ),
            Arguments.of(
                List.of("1 2 3 * 5 + * * 6 5"),
                List.of(
                    List.of("11")
                ),
                List.of("operator * (position: 15): insufficient parameters")
            ),
            Arguments.of(
                List.of("2 3 -", "sqrt"),
                List.of(
                    List.of("-1"),
                    List.of("-1")
                ),
                List.of("", "operator sqrt (position: 1): Attempted square root of negative BigDecimal")
            ),
            Arguments.of(
                List.of("2 3 - 0 /"),
                List.of(
                    List.of("-1", "0")
                ),
                List.of("operator / (position: 9): BigInteger divide by zero")
            )
        );
    }
}
