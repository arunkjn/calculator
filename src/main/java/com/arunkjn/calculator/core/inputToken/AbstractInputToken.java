package com.arunkjn.calculator.core.inputToken;

import java.util.Objects;

public abstract class AbstractInputToken implements InputToken{

    private final String originalToken;
    private final int position;

    public AbstractInputToken(String token, int position) {
        this.originalToken = token;
        this.position = position;
    }

    @Override
    public String getOriginalToken() {
        return originalToken;
    }

    @Override
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
        AbstractInputToken that = (AbstractInputToken) o;
        return position == that.position && originalToken.equals(that.originalToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(originalToken, position);
    }
}
