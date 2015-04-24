package com.corejava.collections.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet;

import org.junit.Test;

import com.corejava.collections.ReverseCollection;

public class ReverseCollectionTest {
    private Double[] array = {2.11, 3.14, 4.9, 5.6};
    
    @Test
    public void reverseLinkedHashsSetTest() {
        Set<Double> set = new LinkedHashSet<>();
        for (int i = 0; i < array.length; i++) {
            set.add(array[i]);
        }
        
        ReverseCollection.reverse(set);
        Iterator<Double> iterator = set.iterator();
        for (int i = array.length - 1; i >= 0; i--){
            assertEquals(array[i], iterator.next());
        }
        
    }
    
    @Test
    public void reverseList() {
        List<Double> list = new ArrayList<>(Arrays.asList(array));
        
        ReverseCollection.reverse(list);
        int n = array.length;
        for (int i = 0; i < list.size(); i++) {
            assertEquals(array[n - i - 1], list.get(i));
        }
       
    }

}
