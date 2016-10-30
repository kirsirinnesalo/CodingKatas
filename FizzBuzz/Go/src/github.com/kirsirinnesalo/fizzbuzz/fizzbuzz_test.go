package main

import (
    "testing"
    "strconv"
)

func TestNumbers(t *testing.T) {
    assert("1", 1, t)
    assert("4", 4, t)
    assert("7", 7, t)
    assert("77", 77, t)
}

func TestFizz(t *testing.T) {
    for i:=1; i<=100; i++ {
        if i%3==0 && i%5!=0{
            assert("Fizz", i, t)
        }
    }
}

func TestBuzz(t *testing.T) {
    for i:=1; i<=100; i++ {
        if i%5==0 && i%3!=0{
            assert("Buzz", i, t)
        }
    }
}

func TestFizzBuzz(t *testing.T) {
    for i:=1; i<=100; i++ {
        if i%3==0 && i%5==0 {
            assert("FizzBuzz", i, t)
        }
    }
}

func assert(expected string, number int, t *testing.T) {
    value := FizzBuzz(number)
    if (value != expected) {
        t.Error("(" + strconv.Itoa(number) + ") expected " + expected + " but was " + value)
    }
}
