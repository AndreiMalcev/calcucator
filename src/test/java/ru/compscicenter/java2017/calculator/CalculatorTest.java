package ru.compscicenter.java2017.calculator;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class CalculatorTest {

    public static final double DELTA = 1E-8;
    Calculator calculator;

    @Before
    public void getInstance() throws ClassNotFoundException, IOException, IllegalAccessException, InstantiationException {
        Properties prop = new Properties();
        prop.load(CalculatorTest.class.getClassLoader().getResourceAsStream("build.properties"));
        Locale.setDefault(Locale.US);
        calculator = (Calculator) CalculatorTest.class.getClassLoader().loadClass(prop.getProperty("IMPLEMENTATION_CLASS")).newInstance();
    }

    @Test
    public void testSimpleExpression() throws Exception {
        assertEquals(3, calculator.calculate("1 + 1*2"), DELTA);
    }

    @Test
    public void testAbs() throws Exception {
        assertEquals(-90.0, calculator.calculate("-Abs(-1E+2 + 20/ 2)"), DELTA);
    }

    @Test
    public void testBacketinScientificDouble() throws Exception {
        assertEquals(200.0, calculator.calculate("1E+2*2"), DELTA);
    }

    @Test
    public void testSin() throws Exception {
        assertEquals(0, calculator.calculate("sin(1E+1^2 - 90 - 10)"), DELTA);
    }

    @Test
    public void testStressScientific1() throws Exception {
        assertEquals(12345678900.0, calculator.calculate("1.23456789E+10"), DELTA);
    }

    @Test
    public void testStressScientific2() throws Exception {
        assertEquals(0.0123456789, calculator.calculate("123456789E-10"), DELTA);
    }
    @Test
    public void testScientific() throws Exception {
        assertEquals(314, calculator.calculate("3.14E+2"), DELTA);
    }

    @Test
    public void testCombination() throws Exception    {
        assertEquals(7.5, calculator.calculate("(4 - 2^3 + 1) * (-(3*3+4*4)^(0.5)) / 2"), DELTA);
    }

    @Test
    public void testDeg1() throws Exception    {
        assertEquals(-243, calculator.calculate("-3^5"), DELTA);
    }

    @Test
    public void testDeg2() throws Exception    {
        assertEquals(-1, calculator.calculate("-(--1)^2"), DELTA);
    }

    @Test
    public void testDeg3() throws Exception    {
        assertEquals(0.0625, calculator.calculate("4^-2"), DELTA);
    }

    @Test
    public void testDeg4() throws Exception    {
        assertEquals(-0.25, calculator.calculate("-4^(2-3)"), DELTA);
    }

    @Test
    public void testDeg5() throws Exception    {
        assertEquals(0.5, calculator.calculate("2^-1^2"), DELTA);
    }

    @Test
    public void testDeg6() throws Exception    {
        assertEquals(1.5, calculator.calculate("2^-abs(-1)+1"), DELTA);
    }
}