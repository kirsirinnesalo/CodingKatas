package com.github.kirsirinnesalo.katas.unclebob.primefactors;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactors {
    public static List<Integer> generate(int n) {
        ArrayList<Integer> primes = new ArrayList<>();
        int candidate = 2;
        while (n>1) {
            for (; n % candidate == 0; n /= candidate) {
                primes.add(candidate);
            }
            candidate++;
        }
        if (n>1) {
            primes.add(n);
        }
        return primes;
    }
}
