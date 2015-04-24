package com.corejava.generics;

import java.util.ArrayList;
import java.util.List;

import com.corejava.generics.Box;


public class BoxExample {
    public static void main(String[] args) {
        Box<Integer> box = new Box<>();
        box.set(3);
        System.out.println(box.get()); //=>3
        
        
        Box<String> aBox = new Box<>("this example belongs to Oracle");
        System.out.println(aBox.get()) ;//"this example belongs to  Oracle"
        
        List<String> fruits = new ArrayList<>();
        fruits.add("kiwi");
        fruits.add("orange");
        fruits.add("lemon");
        
        Box<List<String>> fruitsBox = new Box<>(fruits);
        System.out.println(fruitsBox.get());
        
    }
}
