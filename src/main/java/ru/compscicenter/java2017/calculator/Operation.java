package ru.compscicenter.java2017.calculator;

public interface Operation {
    boolean isThisOperation(String s);

    double resultThisOperation(double a, double b);
}
