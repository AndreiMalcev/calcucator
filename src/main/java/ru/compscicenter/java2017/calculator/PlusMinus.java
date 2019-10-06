package ru.compscicenter.java2017.calculator;

public class PlusMinus implements Operation {

    enum Operation {
        PLUS() {
            @Override
            public double mulDiv(double a, double b) {
                return a + b;
            }
        },
        MINUS() {
            @Override
            public double mulDiv(double a, double b) {
                return a - b;
            }
        };

        abstract double mulDiv(double a, double b);
    }

    private Operation op;

    @Override
    public boolean isThisOperation(String s) {
        switch (s) {
            case ("+"):
                op = Operation.PLUS;
                return true;
            case ("-"):
                op = Operation.MINUS;
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
