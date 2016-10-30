package main

import (
    "fmt"
    "strconv"
)

func FizzBuzz(number int) string {
    if number % 3 == 0 && number % 5 == 0 {
        return "FizzBuzz"
    }
    if number % 3 == 0 {
        return "Fizz"
    }
    if number % 5 == 0 {
        return "Buzz"
    }
    return strconv.Itoa(number)
}

func main() {
    for i:=1; i<=100; i++ {
        fmt.Println(strconv.Itoa(i) + " -> " + FizzBuzz(i))
    }
}
