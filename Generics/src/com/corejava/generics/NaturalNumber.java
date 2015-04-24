package com.corejava.generics;

public class NaturalNumber<T extends Number> {
    public long number;
    
    public NaturalNumber(long number){
        this.number = number;
    }
    
    public boolean isEven(){
        return  number % 2 == 0;
    }
}
