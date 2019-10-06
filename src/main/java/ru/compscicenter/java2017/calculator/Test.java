package ru.compscicenter.java2017.calculator;

import static java.lang.System.out;

public class Test {
    public static void main(String[] args) {
        SimpleCalculator test = new SimpleCalculator();
        String line = "abs(sin(10))";
        out.print(line + " = "+test.calculate(line));
    }
}
