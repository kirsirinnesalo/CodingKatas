package com.github.kirsirinnesalo.katas.fizzbuzzgame;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class GameRulesTest {
    private GameRules rules;

    private void assertAnswerForPopRulesNumber(String expectedAnswer, int number) {
        rules.useRules(GameRules.RULES.POP);
        assertEquals(expectedAnswer, rules.answerFor(number));
    }

    private void assertAnswerForOriginalRulesNumber(String expectedAnswer, int number) {
        rules.useRules(GameRules.RULES.ORIGINAL);
        assertEquals(expectedAnswer, rules.answerFor(number));
    }

    @BeforeMethod
    private void setUp() {
        rules = new GameRules();
    }

    @Test
    public void nonFizzBuzzNumberReturnsNumber() {
        assertAnswerForOriginalRulesNumber("1", 1);
        assertAnswerForOriginalRulesNumber("2", 2);
    }

    @Test
    public void fizzNumberReturnsFizz() {
        assertAnswerForOriginalRulesNumber("Fizz", 3);
        assertAnswerForOriginalRulesNumber("Fizz", 9);
    }

    @Test
    public void buzzNumberReturnsBuzz() {
        assertAnswerForOriginalRulesNumber("Buzz", 5);
        assertAnswerForOriginalRulesNumber("Buzz", 10);
    }

    @Test
    public void fizzBuzzNumberReturnsFizzBuzz() {
        assertAnswerForOriginalRulesNumber("FizzBuzz", 15);
        assertAnswerForOriginalRulesNumber("FizzBuzz", 30);
    }

    @Test
    public void popNumberReturnsPop() {
        assertAnswerForPopRulesNumber("Pop", 7);
        assertAnswerForPopRulesNumber("Pop", 14);
    }

    @Test
    public void fizzPopNumberReturnsFizzPop() {
        assertAnswerForPopRulesNumber("FizzPop", 21);
        assertAnswerForPopRulesNumber("FizzPop", 42);
    }

    @Test
    public void buzzPopNumberReturnsBuzzPop() {
        assertAnswerForPopRulesNumber("BuzzPop", 35);
        assertAnswerForPopRulesNumber("BuzzPop", 70);
    }

    @Test
    public void fizzBuzzPopNumberReturnsFizzBuzzPop() {
        assertAnswerForPopRulesNumber("FizzBuzzPop", 105);
        assertAnswerForPopRulesNumber("FizzBuzzPop", 210);
    }

}
