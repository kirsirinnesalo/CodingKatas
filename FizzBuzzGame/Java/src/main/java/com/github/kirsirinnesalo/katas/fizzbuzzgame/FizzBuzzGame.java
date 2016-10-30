package com.github.kirsirinnesalo.katas.fizzbuzzgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FizzBuzzGame {

    public static final boolean RANDOM_MODE = true;

    private boolean randomMode = !RANDOM_MODE;

    private final UserInterface ui;
    private int score;
    private int rounds;
    private GameRules rules = new GameRules();

    public static void main(String[] args) {
        FizzBuzzGame game = new FizzBuzzGame(new CommandLineUI());
        game.newGame();
        game.playGame();
    }

    public FizzBuzzGame(UserInterface ui) {
        this.ui = ui;
    }

    protected boolean isRandomMode() {
        return randomMode;
    }

    protected GameRules getRules() {
        return rules;
    }

    public int getScore() {
        return score;
    }

    public void newGame() {
        score = 0;
        ui.introduceGame();
        promptUserForSelections();
    }

    protected void promptUserForSelections() {
        rules.useRules(ui.promptRuleSelection());
        randomMode = ui.promptRandomMode();
        rounds = ui.promptRounds();
    }

    public void playGame() {
        for (int number : initNumbers()) {
            ui.ask(number);
            if (isCorrectAnswerForNumber(number, ui.readAnswer())) {
                score++;
            }
        }
        ui.showGameOver();
        ui.showScore(score);
    }

    protected List<Integer> initNumbers() {
        List<Integer> numbers = new ArrayList<Integer>(){{
            for (int i=1; i<=rounds; i++) {
                add(i);
            }
        }};
        if (randomMode) {
            Collections.shuffle(numbers);
        }
        return numbers;
    }

    private boolean isCorrectAnswerForNumber(int number, String playerAnswer) {
        return answer(number).equalsIgnoreCase(playerAnswer);
    }

    public String answer(int number) {
        return rules.answerFor(number);
    }
}
