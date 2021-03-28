package com.arunkjn.calculator.core;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A utility class containing some pure static functions which don't have any dependencies.
 */
public class Utils {
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s+");
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");

    /**
     * Tokenize a user input string into a list of parsed tokens separated by whitespace
     * @param input - raw input string
     * @return list of parsed tokens
     */
    public static List<InputToken> tokenize(String input) {
        if(input == null) {
            throw new IllegalArgumentException("input cannot be null");
        }
        final Matcher whitespaceMatcher = WHITESPACE_PATTERN.matcher(input);
        int lastMatchEnd = 0;
        List<InputToken> result = new ArrayList<>();
        while(whitespaceMatcher.find()) {
            final int matchStart = whitespaceMatcher.start();
            final int matchEnd = whitespaceMatcher.end();
            if(matchStart != 0){
                result.add(new InputToken(input.substring(lastMatchEnd, matchStart), lastMatchEnd));
            }
            lastMatchEnd = matchEnd;
        }
        if(lastMatchEnd < input.length()) {
            result.add(new InputToken(input.substring(lastMatchEnd), lastMatchEnd));
        }
        return result;
    }

    /**
     * Function to check if an input string is numeric. Including a '-' sign and optional decimal value
     * @param input - raw input string
     * @return - true if string is numeric and vice versa
     */
    public static boolean isNumeric(String input) {
        if(input == null) {
            return false;
        }
        return NUMERIC_PATTERN.matcher(input).matches();
    }
}
