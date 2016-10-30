package com.github.kirsirinnesalo.katas.romannumbers;

import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.AssertJUnit.assertEquals;

@Test
public class RomanNumberTest {
    @Test
    public void testSimple() {
        assertResult("I", 1);
        assertResult("V", 5);
        assertResult("X", 10);
        assertResult("L", 50);
        assertResult("C", 100);
        assertResult("D", 500);
        assertResult("M", 1000);
    }

    @Test
    public void testAdditions() {
        assertResult("II", 2);
        assertResult("III", 3);
        assertResult("VII", 7);
        assertResult("VIII", 8);
        assertResult("XIII", 13);
        assertResult("LI", 51);
        assertResult("XV", 15);
        assertResult("LV", 55);
        assertResult("CV", 105);
        assertResult("CLX", 160);
        assertResult("DXX", 520);
        assertResult("DCV", 605);
        assertResult("MCXI", 1111);
    }

    @Test
    public void testSubtractions() {
        assertResult("IV", 4);
        assertResult("IX", 9);
        assertResult("XL", 40);
        assertResult("XLIV", 44);
        assertResult("XC", 90);
        assertResult("CD", 400);
        assertResult("CM", 900);
        assertResult("CMXCIV", 994);
        assertResult("CMXLIX", 949);
        assertResult("CMXLIV", 944);
    }

    @Test
    public void testSomeMeaningfulYears() {
        assertResult("MCMLVII", 1957);
        assertResult("MCMLXXVII", 1977);
        assertResult("MMXIII", 2013);
        assertResult("MMXIV", 2014);
    }

    @Test
    public void testLimits() {
        assertResult("", RomanNumber.MIN - 1);
        assertResult("", RomanNumber.MAX + 1);
        Random random = new Random();
        assertResult("", -random.nextInt(RomanNumber.MAX));
        assertResult("", RomanNumber.MAX + random.nextInt(RomanNumber.MAX));
    }

    private void assertResult(String expected, int value) {
        assertEquals(expected, new RomanNumber().convert(value));
    }
}
