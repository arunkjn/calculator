package com.arunkjn.calculator.core.command;

/**
 * An enum to represent the different types of possible commands string identifiers
 * used to represent them.
 */
public enum CommandType {
    PLUS("+"),
    MINUS("-"),
    DIVIDE("/"),
    MULTIPLY("*"),
    CLEAR("clear"),
    SQRT("sqrt"),
    UNDO("undo"),
    INVALID("");

    private final String value;
    CommandType(String value){
        if(value == null){
            throw new NullPointerException("argument cannot be null");
        }
        this.value = value;
    }

    public static CommandType fromString(String value) {
        for(CommandType operator: CommandType.values()){
            if(value.equals(operator.value)){
                return operator;
            }
        }
        return CommandType.INVALID;
    }

    @Override
    public String toString() {
        return value;
    }
}
