package com.corejava.stackimplementation;

import java.util.Arrays;
import java.util.EmptyStackException;


public class MyStack<E> implements StackInterface<E>{
    private static int DEFAULT_CAPACITY = 5;
    private Object[] elements;
    //count of inserted elements in the array
    private int size = 0; 
    boolean containsDuplicates;
    
    public MyStack(boolean containsDuplicates) {
        this.containsDuplicates = containsDuplicates;
        elements = new Object[DEFAULT_CAPACITY];
    }
    
    
    private void ensureCapacity() {
        int newCapacity = elements.length * 2;
        elements = Arrays.copyOf(elements, newCapacity);
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public void push(E element) {
        if (size == elements.length) {
            ensureCapacity();
        }
        if (containsDuplicates || elementIsNotDuplicated(element)) {
            elements[size++] = element;
        }
    }
    
    @Override
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return (E) elements[--size];
    }
    
    @Override
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return (E) elements[size - 1];
    }
    
    private boolean elementIsNotDuplicated(E element) {
        for (int i = 0; i<size;i++) {
            if (element.equals(elements[i])) {
                return false;
            }
        }
        return true;
    }
   
}
