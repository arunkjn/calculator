package com.arunkjn.calculator.core.command;

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
