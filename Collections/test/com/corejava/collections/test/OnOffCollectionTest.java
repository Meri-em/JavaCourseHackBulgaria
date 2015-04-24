package com.corejava.collections.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.corejava.collections.OnOffCollection;

public class OnOffCollectionTest {
    private OnOffCollection<String> col = new OnOffCollection<>();

    @Test(expected=NullPointerException.class)
    public void addNullElementTest() {
        col.add(null);
    }
    
    @Test
    public void addElementTest() {
        assertTrue(col.add("mandarin"));
        assertTrue(col.add("orange"));
        assertTrue(col.contains("orange"));
        assertTrue(col.contains("mandarin"));
        
        assertFalse(col.add("mandarin"));
        assertTrue(col.contains("orange"));
        assertFalse(col.contains("mandarin"));
    }

}
