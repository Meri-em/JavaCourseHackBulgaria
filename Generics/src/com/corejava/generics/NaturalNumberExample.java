package com.corejava.generics;


public class NaturalNumberExample {
    public static void main(String[] args) {
        
        NaturalNumber<Long> n1 = new NaturalNumber<>(1541L);
        NaturalNumber<Integer> n2 = new NaturalNumber<>(123);
        NaturalNumber<Short> n3 = new NaturalNumber<>((short) 122);
        System.out.println(n1.number);
        System.out.println(n1.isEven()); //false;
        System.out.println(n2.isEven()); //false;
        System.out.println(n3.isEven()); //true;
        
    }
}
