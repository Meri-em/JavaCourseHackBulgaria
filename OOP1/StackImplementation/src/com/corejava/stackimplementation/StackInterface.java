package com.corejava.stackimplementation;

public interface StackInterface<E> {
    boolean isEmpty();
    void push(E element);
    E pop();
    E peek();

}
