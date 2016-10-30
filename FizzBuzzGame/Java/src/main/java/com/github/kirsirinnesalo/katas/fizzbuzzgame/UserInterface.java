package com.github.kirsirinnesalo.katas.fizzbuzzgame;

import static com.github.kirsirinnesalo.katas.fizzbuzzgame.GameRules.RULES;

public interface UserInterface {
    void introduceGame();
    RULES promptRuleSelection();
    boolean promptRandomMode();
    int promptRounds();
    void ask(int number);
    String readAnswer();
    void showGameOver();
    void showScore(int score);
}
