package com.github.kirsirinnesalo.katas.bowling;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class BowlingGameTest {

    private BowlingGame game;

    @BeforeMethod
    private void setUp() {
        game = new BowlingGame();
    }

    @Test
    public void gutterGameScoresZero() {
        game.nextFrame().roll(0);
        assertEquals(0, game.getTotalScore());
    }

    @Test
    public void allOnesScores20() {
        for (int i=1; i<=10; i++) {
            BowlingFrame frame = game.nextFrame();
            frame.roll(1);
            frame.roll(1);
        }
        assertEquals(20, game.getTotalScore());
    }

    @Test
    public void spareInFirstFrameFollowedByThreePinsFollowedByAllMissesScore16() {
        rollFrame(9,1);
        rollFrame(3, 0);
        assertEquals(16, game.getTotalScore());
    }

    @Test
    public void strikeInFirstFrameFollowedByThreeAndThenFourPinsFollowedByAllMissesScore24() {
        rollFrame(10,0);
        rollFrame(3,4);
        assertEquals(24, game.getTotalScore());
    }

    @Test
    public void perfectGameScores300() {
        for (int i=1; i<=11; i++) {
            rollFrame(10,0);
        }
        assertEquals(300, game.getTotalScore());
    }

    private void rollFrame(int firstRoll, int secondRoll) {
        BowlingFrame frame = game.nextFrame();
        frame.roll(firstRoll);
        frame.roll(secondRoll);
    }
}
