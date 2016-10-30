package com.github.kirsirinnesalo.katas.bowling;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
    private List<BowlingFrame> frames = new ArrayList<>();

    public BowlingFrame nextFrame() {
        BowlingFrame frame = new BowlingFrame();
        frames.add(frame);
        return frame;
    }

    public int getTotalScore() {
        int totalScore = 0;
        int frameIndex = 0;
        for (BowlingFrame frame : frames) {
            if (frame.isStrike()) {
                totalScore += 10 + getStrikeBonus(frameIndex);
            } else if (frame.isSpare()) {
                totalScore += 10 + getSpareBonus(frameIndex);
            } else {
                totalScore += frame.getScore();
            }
            frameIndex++;
        }
        return totalScore;
    }

    private int getStrikeBonus(int frameIndex) {
        if (isBonusFrame(frameIndex)) {
            return 0;
        }
        BowlingFrame nextFrame = frames.get(frameIndex + 1);
        if (nextFrame.isStrike() && isFramesLeft(frameIndex)) {
            return nextFrame.getScore() + frames.get(frameIndex + 2).getFirstRoll();
        }
        return nextFrame.getScore();
    }

    private boolean isFramesLeft(int frameIndex) {
        return frameIndex < 9;
    }

    private boolean isBonusFrame(int frameIndex) {
        return frameIndex > 9;
    }

    private int getSpareBonus(int frameIndex) {
        return frames.get(frameIndex+1).getFirstRoll();
    }
}
