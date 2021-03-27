package com.arunkjn.calculator.core.operator;

public enum OperatorType {
    PLUS("+"),
    MINUS("-"),
    DIVIDE("/"),
    MULTIPLY("*"),
    CLEAR("clear"),
    SQRT("sqrt"),
    INVALID("");

    private final String value;
    OperatorType(String value){
        this.value = value;
    }

    public static OperatorType fromString(String value) {
        for(OperatorType operator: OperatorType.values()){
            if(value.equals(operator.value)){
                return operator;
            }
        }
        return OperatorType.INVALID;
    }

    @Override
    public String toString() {
        return value;
    }
}
