package com.github.kirsirinnesalo.katas.fizzbuzzgame;

public class PopRules implements Rules {
    @Override
    public String answerFor(int number) {
        String answer = Integer.toString(number);
        if ((number % 3 == 0) && (number % 5 == 0) && (number % 7 == 0)) {
            answer = "FizzBuzzPop";
        } else if ((number % 3 == 0) && (number % 5 == 0)) {
            answer = "FizzBuzz";
        } else if ((number % 3 == 0) && (number % 7 == 0)) {
            answer = "FizzPop";
        } else if ((number % 5 == 0) && (number % 7 == 0)) {
            answer = "BuzzPop";
        } else if (number % 3 == 0) {
            answer = "Fizz";
        } else if (number % 5 == 0) {
            answer = "Buzz";
        } else if (number % 7 == 0) {
            answer = "Pop";
        }
        return answer;
    }
}
