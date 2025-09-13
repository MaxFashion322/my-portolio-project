package org.example;

public final class InputValidator {
    private InputValidator(){}

    public static boolean isPositiveDouble(String input, String err1, String err2) {
        try {
            return Double.parseDouble(input) > 0;
        }catch(NumberFormatException e){
            System.err.println(err1);
            System.err.println(err2);
        }
        return false;
    }

    public static boolean isPositiveInt(String input, String err1, String err2) {
        try {
            return Integer.parseInt(input) > 0;
        }catch(NumberFormatException e){
            System.err.println(err1);
            System.err.println(err2);
        }
        return false;
    }
}

