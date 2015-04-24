package com.corejava.files.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.corejava.files.DayEnum;
import com.corejava.files.DayEnum.Day;

public class DayEnumTest {

    @Test
    public void testDayEnum(){
        assertEquals("Hello", DayEnum.greetMe(Day.MONDAY));
        assertEquals("Holla", DayEnum.greetMe(Day.FRIDAY));
        assertEquals("Good evening", DayEnum.greetMe(Day.SUNDAY));
        
    }

    
}
