package com.arunkjn.calculator.core.inputToken;

public interface InputToken {
    /*
    value of this token as present in the original string
     */
    String getOriginalToken();
    /*
    zero based index position of this token in the original string
     */
    int getPositionInInputString();
}
