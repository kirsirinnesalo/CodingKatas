package com.github.kirsirinnesalo.katas.fizzbuzzgame;

import org.apache.commons.lang3.StringUtils;

import java.io.PrintStream;
import java.util.Scanner;

public class CommandLineUI implements UserInterface {
    private final PrintStream out = System.out;
    private final Scanner scanner = new Scanner(System.in);
    private String lineSeparator = System.lineSeparator();

    private int rounds;

    @Override
    public void introduceGame() {
        String intro = "FizzBuzz Game";
        intro += lineSeparator+lineSeparator;
        intro += "FizzBuzz is a children's counting and substitution game, whereby you count up to a limit " +
                lineSeparator +
                "replacing some numbers with words. In the classic game you replace any number which is a multiple " +
                lineSeparator +
                "of 3 with Fizz and any number which is a multiple of 5 with Buzz, and any number which is a " +
                lineSeparator +
                "multiple of both 3 and 5 with FizzBuzz.";
        intro += lineSeparator+lineSeparator;
        intro += "There's also an additional rule possibility. It is called 'Pop' which occurs on multiples of 7.";
        intro += lineSeparator+lineSeparator;
        out.println(intro);
    }

    @Override
    public GameRules.RULES promptRuleSelection() {
        String prompt = "Select rules for game:" + lineSeparator;
        for (GameRules.RULES rule : GameRules.RULES.values()) {
            prompt += "   " + rule.selectionValue() + ") " + rule.description() + lineSeparator;
        }
        prompt += "> ";
        out.print(prompt);
        return GameRules.RULES.getTypeForSelection(scanner.nextInt());
    }

    @Override
    public boolean promptRandomMode() {
        out.print("Play random mode [y/N]? > ");
        String answer = scanner.next();
        return StringUtils.equalsIgnoreCase("y",answer);
    }

    @Override
    public int promptRounds() {
        out.print("How many rounds do you want to play? > ");
        rounds = scanner.nextInt();
        out.println();
        return rounds;
    }

    @Override
    public void ask(int number) {
        out.print(number + " > ");
    }

    @Override
    public String readAnswer() {
        return scanner.next();
    }

    @Override
    public void showGameOver() {
        out.println();
        out.println("Game over.");
    }

    @Override
    public void showScore(int score) {
        out.println();
        out.println("You got " + score + " of " + rounds + " correct answers.");
    }

}
