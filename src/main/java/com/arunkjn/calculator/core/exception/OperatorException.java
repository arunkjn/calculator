package com.arunkjn.calculator.core.exception;

/**
 * Exception to encapsulate the failure of an operator
 */
public class OperatorException extends RuntimeException {
    public OperatorException(String operator, int position, String message) {
        super(
            String.format(
                "operator %s (position: %d): %s",
                operator,
                position,
                message
            )
        );
    }
}
