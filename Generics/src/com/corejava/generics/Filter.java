package com.corejava.generics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

//for example Integer extends Comparable<T>
public class Filter<T> {
    
    public static <T extends Comparable<T>> Collection<T> greaterThan(Collection<T> collection, T value){
        Collection<T> result = new ArrayList<T>();
        for(T e: collection){
            if(e.compareTo(value) > 0)
                result.add(e);
        }
        return result;
    }
    public static void main(String[] args) {
        Collection<Integer> all = Arrays.asList(1,2,3,4,5,6,7);
        Integer value = 5;
        Collection<Integer> filtered = greaterThan(all, value); //6, 7
        System.out.println(filtered);
        
    }
}
