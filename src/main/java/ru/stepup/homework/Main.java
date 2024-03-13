package ru.stepup.homework;

public class Main {
    public static void main(String[] args) {
        Fraction fr = new Fraction(2,3);
        Fractionable num = Utils.cache(fr);
        num.doubleValue();
        num.doubleValue();
        num.doubleValue();
        num.setNum(5);
        num.doubleValue();
        num.doubleValue();
    }
}