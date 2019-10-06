package ru.compscicenter.java2017.calculator;

import static java.lang.Character.isDigit;

public class Algorithm {
    private String expression;
    private int position = 0;

    Algorithm(String expression) {
        this.expression = expression;
    }

    private void nextPosition() {
        position++;
    }

    private void nextPosition(int k) {
        for (int i = 0; i < k; i++) {
            position++;
        }
    }


    private char thisSymbol() {
        if (position < expression.length()) {
            return expression.charAt(position);
        } else {
            return '@';
        }
    }

    double parse() {
        expression = expression.replace(" ", "");
        expression = expression.toLowerCase();
        return parsePlusMinus();
    }

    private double parsePlusMinus() {
        double x = parseMulDiv();
        Operation plusMinus = new PlusMinus();
        while (true) {
            if (plusMinus.isThisOperation(String.valueOf(thisSymbol()))) {
                nextPosition();
                x = plusMinus.resultThisOperation(x, parseMulDiv());
            } else {
                return x;
            }
        }
    }

    private double parseMulDiv() {
        double x = parseUnarySign();
        while (true) {
            Operation mulDiv = new MulDiv();
            while (true) {
                if (mulDiv.isThisOperation(String.valueOf(thisSymbol()))) {
                    nextPosition();
                    x = mulDiv.resultThisOperation(x, parseUnarySign());
                } else {
                    return x;
                }
            }
        }
    }

    private int signNegative() {
        int sign = 1;
        while (thisSymbol() == '-') {
            sign = -1 * sign;
            nextPosition();
        }
        return sign;
    }

    private double parseUnarySign() {
        return signNegative() * parseDeg();
    }


    private double parseDeg() {
        double x = parseBracket();
        while (true) {
            if (thisSymbol() == '^') {
                nextPosition();
                double y = parseUnarySign();
                x = Math.pow(x, y);
            } else {
                return x;
            }
        }
    }


    private double parseBracket() {
        switch (thisSymbol()) {
            case '(':
                nextPosition();
                double x = parsePlusMinus();
                if (thisSymbol() == ')') {
                    nextPosition();
                    return x;
                }
                break;
            default:
        }
        return parseFunction();
    }

    private double parseFunction() {
        Function function = new Function();
        if (function.isThisOperation(expression.substring(position))) {
            nextPosition(function.lengthNameFunction());
            return function.resultThisOperation(parseInFunction());
        }
        return parseNum();
    }

    private double parseInFunction() {
        StringBuilder inFunction = new StringBuilder();
        inFunction.append(thisSymbol());
        nextPosition();
        int braket = 1;
        while (braket != 0) {
            switch (thisSymbol()) {
                case '(':
                    ++braket;
                    break;
                case ')':
                    --braket;
                    break;
                default:
            }
            inFunction.append(thisSymbol());
            nextPosition();
        }
        Algorithm temp = new Algorithm(inFunction.toString());
        return temp.parse();
    }


    private double parseNum() {
        StringBuilder number = parseUsuallyNum();
        if (thisSymbol() == 'e') {
            number.append("E");
            nextPosition();
            number.append(thisSymbol());
            nextPosition();
            number.append(parseUsuallyNum());
        }
        return Double.valueOf(number.toString());
    }

    private StringBuilder parseUsuallyNum() {
        StringBuilder number = new StringBuilder();
        while (isDigit(thisSymbol()) || thisSymbol() == '.') {
            number.append(thisSymbol());
            nextPosition();
        }
        return number;
    }
}