package com.arunkjn.calculator.core.parsing;

import java.util.Objects;

/**
 * Class used to represent a parsed token from a user input string
 */
public class InputToken {
    /**
     * The original token as present in the string
     */
    private final String originalToken;
    /**
     * The non-zero index position of token in the original string
     */
    private final int position;

    public InputToken(String token, int position) {
        if(token == null) {
            throw new NullPointerException("arguments cannot be null");
        }
        this.originalToken = token;
        this.position = position;
    }

    public String getOriginalToken() {
        return originalToken;
    }

    public int getPositionInInputString() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InputToken that = (InputToken) o;
        return position == that.position && originalToken.equals(that.originalToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(originalToken, position);
    }
}
