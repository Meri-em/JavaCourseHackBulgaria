package com.corejava.generics;
public class Box<T> {
    private T object;
    
    public Box(){
        
    }
    
    public Box(T object){
        this.object = object;
    }
    
    public void set(T object){
        this.object = object;
    }
    
    public T get(){
        return object;
    }
}
