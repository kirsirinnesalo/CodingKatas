package com.github.kirsirinnesalo.katas.fizzbuzzgame;

import org.apache.commons.lang3.StringUtils;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

public class FizzBuzzGameTest {

    private UserInterface ui;
    private FizzBuzzGame game;

    @BeforeMethod
    private void setUp() {
        ui = mock(UserInterface.class);
        game = new FizzBuzzGame(ui);
    }

    private void mockUserSelections(GameRules.RULES rules, boolean randomMode, int rounds) {
        when(ui.promptRuleSelection()).thenReturn(rules);
        when(ui.promptRandomMode()).thenReturn(randomMode);
        when(ui.promptRounds()).thenReturn(rounds);
    }

    private void mockDummyNewGame() {
        mockGameWith(GameRules.RULES.ORIGINAL, 0);
    }

    private void mockGameWith(GameRules.RULES rules, int rounds) {
        mockGameWith(rules, !FizzBuzzGame.RANDOM_MODE, rounds);
    }

    private void mockGameWith(GameRules.RULES rules, boolean randomMode, int rounds) {
        mockUserSelections(rules, randomMode, rounds);
        game.newGame();
    }

    private void mockCorrectAnswers() {
        when(ui.readAnswer()).thenAnswer(new Answer<Object>() {
            private int count = 1;
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return game.getRules().answerFor(count++);
            }
        });
    }

    private void mockAnswers(String... answers) {
        final List<String> playerAnswers = Arrays.asList(answers);
        when(ui.readAnswer()).thenAnswer(new Answer<Object>() {
            private int i = 0;
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return playerAnswers.get(i++);
            }
        });
    }

    private void assertCorrectlyPlayedRounds(int rounds) {
        mockCorrectAnswers();
        assertPlayedRounds(rounds);
        assertEquals("Expected correct answers for all " + rounds + " rounds.", rounds, game.getScore());
    }

    private void assertPlayedRounds(int rounds) {
        game.playGame();
        verify(ui, times(rounds)).readAnswer();
    }

    @Test
    public void introductionIsShownAndUserIsPromptForRulesAndRoundsWhenGameStarts() {
        mockDummyNewGame();
        verify(ui, times(1)).introduceGame();
    }

    @Test
    public void randomModeIsPromptedWhenGameStarts() {
        mockDummyNewGame();
        verify(ui, times(1)).promptRandomMode();
    }

    @Test
    public void rulesArePromptedWhenGameStarts() {
        mockDummyNewGame();
        verify(ui, times(1)).promptRuleSelection();
    }

    @Test
    public void playerIsPromptedForRoundsWhenGameStarts() {
        mockDummyNewGame();
        verify(ui, times(1)).promptRounds();
    }

    @Test
    public void enteredRoundsArePlayed() {
        mockGameWith(GameRules.RULES.ORIGINAL, 7);
        assertCorrectlyPlayedRounds(7);
        verify(ui, times(1)).showGameOver();
        verify(ui, times(1)).showScore(7);
    }

    @Test
    public void scoreIsCalculatedWithCorrectAnswers() {
        mockGameWith(GameRules.RULES.ORIGINAL, 2);
        assertCorrectlyPlayedRounds(2);
        assertEquals(2, game.getScore());
    }

    @Test
    public void scoreIsCalculatedWithIncorrectAnswers() {
        mockGameWith(GameRules.RULES.ORIGINAL, 2);
        mockAnswers("1", "1");
        assertPlayedRounds(2);
        assertEquals(1, game.getScore());
    }

    @Test
    public void scoreIsPrintedAfterTheGame() {
        mockGameWith(GameRules.RULES.ORIGINAL, 3);
        assertCorrectlyPlayedRounds(3);
        verify(ui, times(1)).showScore(3);
    }

    @Test
    public void caseDifferenceIsIgnoredInAnswer() {
        mockGameWith(GameRules.RULES.ORIGINAL, 3);
        mockAnswers("1", "2", "fizz");
        assertCorrectlyPlayedRounds(3);
    }

    private void assertSelectedRulesCanBePlayed(GameRules.RULES rules) {
        mockGameWith(rules, 7);
        assertCorrectlyPlayedRounds(7);
    }

    @Test
    public void popRulesCanBePlayed() {
        assertSelectedRulesCanBePlayed(GameRules.RULES.POP);
    }

    @Test
    public void originalRulesCanBeSelected() {
        assertSelectedRulesCanBePlayed(GameRules.RULES.ORIGINAL);
    }

    @Test
    public void randomNumberModeCanBePlayed() {
        List<Integer> numbers = Arrays.asList(9, 4, 1, 3, 10, 7, 5, 8, 2, 6);
        game = new FizzBuzzGame(ui) {
            @Override
            protected List<Integer> initNumbers() {
                if (isRandomMode()) {
                    return numbers;
                } else {
                    return super.initNumbers();
                }
            }
        };
        mockGameWith(GameRules.RULES.ORIGINAL, FizzBuzzGame.RANDOM_MODE, 10);
        mockAnswers("fizz", "4", "1", "fizz", "buzz", "7", "buzz", "8", "2", "fizz");
        assertPlayedRounds(10);
        assertEquals(10, game.getScore());
    }

    @Test
    public void numbersAreRandomizedInRandomMode() {
        //TODO how to confirm that Collections.shuffle is used?
        mockGameWith(GameRules.RULES.POP, FizzBuzzGame.RANDOM_MODE, 10);
        String numbers1 = StringUtils.join(game.initNumbers(), ",");
        String numbers2 = StringUtils.join(game.initNumbers(), ",");
        assertFalse("Expected that lists differs, but they are same." + System.lineSeparator() +
                numbers1 + System.lineSeparator() + numbers2,
                numbers1.equals(numbers2));
    }
}
