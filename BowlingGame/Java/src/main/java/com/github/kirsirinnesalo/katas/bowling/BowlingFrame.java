package com.github.kirsirinnesalo.katas.bowling;

public class BowlingFrame {
    private int[] rolls = new int[2];
    private int roll = 0;

    public void roll(int pins) {
        rolls[roll] = pins;
        roll++;
    }

    public int getScore() {
        return rolls[0]+rolls[1];
    }

    public boolean isStrike() {
        return rolls[0] == 10;
    }

    public boolean isSpare() {
        return !isStrike() && rolls[0] + rolls[1] == 10;
    }

    public int getFirstRoll() {
        return rolls[0];
    }
}
