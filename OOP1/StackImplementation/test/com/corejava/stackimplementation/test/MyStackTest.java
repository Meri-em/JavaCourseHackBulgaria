package com.corejava.stackimplementation.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.corejava.stackimplementation.MyStack;

public class MyStackTest {
    private MyStack<Integer> stackWithoutDuplicates = new MyStack<>(false); 
    private MyStack<Integer> stack = new MyStack<>(true); 

    @Before
    public void setUp() throws Exception {
        stack.push(2);
        stack.push(3);
        stack.push(3);
        stack.push(4);
        
        stackWithoutDuplicates.push(2);
        stackWithoutDuplicates.push(3);
        stackWithoutDuplicates.push(3);
        stackWithoutDuplicates.push(4);
    }

    @Test
    public void stackTest() {
        assertEquals(new Integer(4), stack.peek());
        assertEquals(new Integer(4), stack.pop());
        assertEquals(new Integer(3), stack.pop());
        assertEquals(new Integer(3), stack.pop());
        assertEquals(false, stack.isEmpty());
        assertEquals(new Integer(2), stack.pop());
        assertEquals(true, stack.isEmpty());
    }
    
    @Test
    public void stackWithoutDuplicatesTest() {
        assertEquals(new Integer(4), stackWithoutDuplicates.peek());
        assertEquals(new Integer(4), stackWithoutDuplicates.pop());
        assertEquals(new Integer(3), stackWithoutDuplicates.pop());
        assertEquals(false, stackWithoutDuplicates.isEmpty());
        assertEquals(new Integer(2), stackWithoutDuplicates.pop());
        assertEquals(true, stackWithoutDuplicates.isEmpty());
    }

}
