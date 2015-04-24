package com.corejava.collections.test;

import static org.junit.Assert.*;
import com.corejava.collections.FindDuplicates;
import java.util.Set;
import java.util.HashSet;

import org.junit.Test;

public class FindDuplicatesTest {
    private FindDuplicates fd = new FindDuplicates();

    @Test
    public void test() {
        Set<Integer> first = new HashSet<>();
        first.add(7);
        first.add(12);
        first.add(6);
        first.add(42);
        
        Set<Integer> second = new HashSet<>();
        second.add(12);
        second.add(42);
        second.add(13);
        
        Set<Integer> third = new HashSet<>();
        third.add(6);
        third.add(13);
        third.add(42);
        third.add(0);
        third.add(9);
        third.add(12);
        
        Set<Integer> result = new HashSet<>();
        result.add(12);
        result.add(42);
        
        assertEquals(result, fd.findDuplicateElements(first, second, third));
        assertEquals(result, fd.findDuplicateElements(result));
        assertTrue(fd.findDuplicateElements().isEmpty());
    }

}
