package com.corejava.collections.test;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.corejava.collections.CountWords;

public class CountWordsTest {
    private LinkedHashMap<String, Integer> map;
    
    @Before
    public void setUp() {
        map = new LinkedHashMap<>();
        map.put("Ninjas", 1);
        map.put("are", 2);
        map.put("all", 2);
        map.put("over", 1);
        map.put("the", 1);
        map.put("place!", 1);
        map.put("We", 1);
        map.put("going", 1);
        map.put("to", 1);
        map.put("die!", 1);
    }

    @Test
    public void countWordsTest(){
       String text = "Ninjas are all over the place! We are all going to die!";
       assertEquals(map, CountWords.countWords(text));
    }
   
    @Test
    public void printMessageTest() {
        assertEquals("{ Ninjas:1, are:2, all:2, over:1, the:1, place!:1, We:1, going:1, to:1, die!:1 }",CountWords.printMessage(map));
    }


}
