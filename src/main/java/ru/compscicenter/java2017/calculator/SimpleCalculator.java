package ru.compscicenter.java2017.calculator;

public class SimpleCalculator implements Calculator {
    @Override
    public double calculate(String line) {
        Algorithm temp = new Algorithm(line);
        return temp.parse();
    }
}
