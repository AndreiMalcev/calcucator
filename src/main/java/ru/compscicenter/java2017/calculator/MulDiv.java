package ru.compscicenter.java2017.calculator;

public class MulDiv implements Operation {
    enum Operation {
        MUL() {
            @Override
            public double mulDiv(double a, double b) {
                return a * b;
            }
        },
        DIV() {
            @Override
            public double mulDiv(double a, double b) {
                return a / b;
            }
        };

        abstract double mulDiv(double a, double b);
    }

    private Operation op;

    @Override
    public boolean isThisOperation(String s) {
        switch (s) {
            case ("*"):
                op = Operation.MUL;
                return true;
            case ("/"):
                op = Operation.DIV;
                return true;
            default:
                return false;
        }
    }

    @Override
    public double resultThisOperation(double a, double b) {
        return op.mulDiv(a, b);
    }
}
