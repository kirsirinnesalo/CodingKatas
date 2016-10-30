package com.github.kirsirinnesalo.katas.fizzbuzzgame;

public class OriginalRules implements Rules {
    @Override
    public String answerFor(int number) {
        String answer = Integer.toString(number);
        if ((number % 3 == 0) && (number % 5 == 0)) {
            answer = "FizzBuzz";
        } else if (number % 3 == 0) {
            answer = "Fizz";
        } else if (number % 5 == 0) {
            answer = "Buzz";
        }
        return answer;
    }
}
