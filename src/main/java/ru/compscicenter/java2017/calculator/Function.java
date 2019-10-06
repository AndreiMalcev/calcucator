package ru.compscicenter.java2017.calculator;

public class Function {
    enum Func {
        sin() {
            @Override
            public double func(double x) {
                return Math.sin(x);
            }
        },
        cos() {
            @Override
            public double func(double x) {
                return Math.cos(x);
            }
        },
        abs() {
            @Override
            public double func(double x) {
                return Math.abs(x);
            }
        };

        abstract double func(double x);
    }

    private Function.Func op;

    boolean isThisOperation(String s) {
        for (Func f : Func.values()) {
            if (s.startsWith(f.name())) {
                op = f;
                return true;
            }
        }
        return false;
    }

    public double resultThisOperation(double x) {
        return op.func(x);
    }

    public int lengthNameFunction() {
        return op.name().length();
    }
}
