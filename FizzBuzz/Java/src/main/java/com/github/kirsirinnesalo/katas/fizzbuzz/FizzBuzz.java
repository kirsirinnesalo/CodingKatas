package com.github.kirsirinnesalo.katas.fizzbuzz;

public class FizzBuzz {
    public void print(int number) {
        String result = Integer.toString(number);
        if ((number % 3 == 0) && (number % 5 == 0)) {
            result = "FizzBuzz";
        } else  if (number % 3 == 0) {
            result = "Fizz";
        } else if (number % 5 == 0) {
            result = "Buzz";
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        FizzBuzz program = new FizzBuzz();
        for (int i=1; i<=100; i++) {
            System.out.print(i + " => ");
            program.print(i);
        }
    }
}
