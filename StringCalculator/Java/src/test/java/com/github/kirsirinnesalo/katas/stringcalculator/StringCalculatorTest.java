package com.github.kirsirinnesalo.katas.stringcalculator;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class StringCalculatorTest {
    private StringCalculator calculator = new StringCalculator();

    @Test
    public void emptyStringReturnsZero() {
        assertResult(0, "");
    }

    @Test
    public void singleNumberReturnsValue() {
        assertResult(3, "3");
    }

    @Test
    public void twoNumbersCommaDelimitedReturnsSum() {
        assertResult(7, "3,4");
    }

    @Test
    public void twoNumbersNewlineDelimitedReturnsSum() {
        assertResult(7, "3\n4");
    }

    @Test
    public void threeNumbersDelimitedEitherWayReturnsSum() {
        assertResult(13, "3,4,6");
        assertResult(13, "3\n4\n6");
        assertResult(13, "3\n4,6");
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void negativeNumbersThrowException() {
        calculator.add("-1");
    }

    @Test
    public void silentlyIgnoreNumbersGreaterThanThousand() {
        assertResult(1000, "1000");
        assertResult(1001, "1000,1");
        assertResult(2, "1001,2");
    }

    @Test
    public void singleCharDelimiterCanBeDefined() {
        assertResult(7, "//#\n3#4");
    }

    @Test
    public void delimiterCanBeAnyLength() {
        assertResult(7, "//[***]\n3***4");
    }

    @Test
    public void manyMulticharDelimitersCanBeDefined() {
        assertResult(13, "//[##][***]\n3##4***6");
    }

    private void assertResult(int expected, String value) {
        assertEquals(expected, calculator.add(value));
    }
}
