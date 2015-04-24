package com.corejava.collections;

import java.util.Collection;
import java.util.Stack;

public class ReverseCollection {
    public static <T> void reverse(Collection<T> collection) {
        Stack<T> stack = new Stack<>();
        for (T elements: collection) {
            stack.push(elements);
        }
        
        collection.clear();
        while (!stack.isEmpty()){
            collection.add(stack.pop());
        }
    }
}
