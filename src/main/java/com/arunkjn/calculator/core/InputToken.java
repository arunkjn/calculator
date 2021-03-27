package com.arunkjn.calculator.core;

import java.util.Objects;

public class InputToken {

    private final String originalToken;
    private final int position;

    public InputToken(String token, int position) {
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
