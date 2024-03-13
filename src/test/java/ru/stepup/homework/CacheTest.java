package ru.stepup.homework;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CacheTest {
    // <!-- Скопировано с https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
   private final PrintStream originalOut = System.out;


    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
    // конец копипаста --!>
    @Test
    public void out() {
        Fraction fr = new Fraction(2,3);
        Fractionable num = Utils.cache(fr);
        num.doubleValue(); //первый вызов, кэш пустой, печатается "invoke double value"
        Assertions.assertEquals("invoke double value\n", outContent.toString());
        outContent.reset(); //очищаем экран
        num.doubleValue();
        Assertions.assertEquals("", outContent.toString()); //проверяем что на экран ничего не вывелось
        num.doubleValue();
        Assertions.assertEquals("", outContent.toString());
        num.setNum(5);
        num.doubleValue(); //значение объекта изменилось, кеш обновился, выводится сообщение
        Assertions.assertEquals("invoke double value\n", outContent.toString());
        outContent.reset(); //очищаем экран
        num.doubleValue();
        Assertions.assertEquals("", outContent.toString());
    }
    @Test
    public void denumTest(){
        Fraction fr = new Fraction(1,2);
        Assertions.assertEquals((double) fr.doubleValue(),(double) 1/2);
    }

}
