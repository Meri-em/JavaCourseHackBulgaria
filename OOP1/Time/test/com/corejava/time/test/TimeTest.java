package com.corejava.time.test;

import static org.junit.Assert.*;
import org.junit.Test;

import com.corejava.time.Time;

public class TimeTest {

    @Test
    public void test() {
        Time time1 = new Time(21, 9, 47, 12, 3, 2014);
        Time time2 = new Time(1, 59, 20, 31, 1, 1988);
        assertEquals("21:09:47 12.03.14", time1.toString());
        assertEquals("01:59:20 31.01.88", time2.toString());
        
        System.out.println(new Time().now());

    }

}
