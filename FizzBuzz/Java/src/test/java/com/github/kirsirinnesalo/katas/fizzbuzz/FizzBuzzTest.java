package com.github.kirsirinnesalo.katas.fizzbuzz;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.testng.AssertJUnit.assertEquals;

public class FizzBuzzTest {

    private FizzBuzz fizzBuzz;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOutStream;

    @BeforeClass
    private void setUpEnv() {
        originalOutStream = System.out;
    }

    @AfterClass private void cleanUpEnv() {
        System.setOut(originalOutStream);
    }

    @BeforeMethod
    private void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        fizzBuzz = new FizzBuzz();
    }

    private void assertOutput(String expectedValue, int... numbers) {
        for (int number : numbers) {
            fizzBuzz.print(number);
        }
        assertEquals(separate(expectedValue), outContent.toString());
    }

    private String separate(String values) {
        String separatedValues = "";
        for (String value : StringUtils.split(values, ",")) {
            separatedValues += value + System.lineSeparator();
        }
        return separatedValues;
    }

    @Test public void testNumbers() {
        assertOutput("1,2,4,7,8", 1, 2, 4, 7, 8);
    }

    @Test
    public void divisibleByThreeResultsFizz() {
        assertOutput("Fizz,Fizz,Fizz,Fizz,Fizz", 3, 6, 9, 12, 18);
    }

    @Test
    public void divisibleByFiveResultsBuzz() {
        assertOutput("Buzz,Buzz,Buzz,Buzz", 5, 10, 20, 25);
    }

    @Test public void divisibleByThreeAndFiveResultsFizzBuzz() {
        assertOutput("FizzBuzz,FizzBuzz,FizzBuzz", 15, 30, 45);
    }

}
