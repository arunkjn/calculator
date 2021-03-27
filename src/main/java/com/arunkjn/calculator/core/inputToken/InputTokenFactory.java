package com.arunkjn.calculator.core.inputToken;

import com.arunkjn.calculator.core.Utils;
import com.arunkjn.calculator.core.inputToken.impl.NumericInputToken;
import com.arunkjn.calculator.core.inputToken.impl.OperatorInputToken;

public class InputTokenFactory {

    public static InputToken getInputToken(String token, int position){
        if(Utils.isNumeric(token)){
            return new NumericInputToken(token, position);
        } else {
            return new OperatorInputToken(token, position);
        }
    }
}
