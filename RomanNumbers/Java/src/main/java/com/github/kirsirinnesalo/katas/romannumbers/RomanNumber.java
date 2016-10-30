package com.github.kirsirinnesalo.katas.romannumbers;

public class RomanNumber {
    protected static final int MIN = 1;
    protected static final int MAX = 3000;

    private enum ROMAN {
        M("M", 1000),
        D("D", 500),
        C("C", 100),
        L("L", 50),
        X("X", 10),
        V("V", 5),
        I("I", 1);
        String romanValue;
        int arabicValue;

        ROMAN(String s, int i) {
            this.romanValue = s;
            this.arabicValue = i;
        }
    }

    private ROMAN[] subtractions = new ROMAN[]{ROMAN.C, ROMAN.X, ROMAN.I};

    private String result = "";

    public String convert(int number) {
        result = "";
        if (MIN <= number && number <= MAX) {
            calculateResultFor(number);
        }
        return result;
    }

    private void calculateResultFor(int number) {
        for (ROMAN roman : ROMAN.values()) {
            while (number >= roman.arabicValue) {
                result += roman.romanValue;
                number -= roman.arabicValue;
            }
            if (number < MIN) break;
            number = calculateSubtractionFor(number);
        }
    }

    private int calculateSubtractionFor(int number) {
        for (ROMAN roman : ROMAN.values()) {
            if (roman == ROMAN.I) break;
            ROMAN sub = getSubtraction(number);
            if (sub.arabicValue < number && number < roman.arabicValue) {
                if (number >= roman.arabicValue - sub.arabicValue) {
                    result += sub.romanValue + roman.romanValue;
                    number -= roman.arabicValue - sub.arabicValue;
                    break;
                }
            }
        }
        return number;
    }

    private ROMAN getSubtraction(int value) {
        for (ROMAN sub : subtractions) {
            if (value > sub.arabicValue) return sub;
        }
        return ROMAN.I;
    }
}
