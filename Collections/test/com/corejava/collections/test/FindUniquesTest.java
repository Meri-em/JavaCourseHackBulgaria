package com.corejava.collections.test;

import static org.junit.Assert.*;

import com.corejava.collections.FindUniques;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

import org.junit.Test;

public class FindUniquesTest {
    private FindUniques fu = new FindUniques();

    @Test
    public void findUniquesListTest() {
       List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,5,4,3,1));
       assertEquals(fu.fistFirstUniqueElement(list), new Integer(2));
    }
    
    @Test
    public void findUniquesStackTest() {
       Stack<Character> stack = new Stack<>();
       stack.push('a');
       stack.push('b');
       stack.push('c');
       stack.push('d');
       stack.push('a');
       stack.push('d');
       stack.push('e');
       stack.push('b');
       stack.push('a');
       stack.push('f');
       stack.push('c');
       
       assertEquals(new Character('e'), fu.fistFirstUniqueElement(stack));
    }

}
