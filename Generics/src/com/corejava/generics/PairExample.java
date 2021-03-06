package com.corejava.generics;

public class PairExample {
    public static void main(String[] args) {
      Pair<Integer,String> pair = new Pair<>();
      pair.setFirst(3);
      pair.setSecond("string");
      
      System.out.println(pair.toString()); //=> <3, "string"> (Implement the to string method as well) 
      System.out.println(pair.first()); //=> 3 (first() here returns an *Integer*, not an *Object*!)
      System.out.println(pair.second()); //=> "string"
      
      Pair<Integer, String> pair1 = new Pair<>(3, "string");
      System.out.println(pair1.toString()); //=> <3, "string"> 
      
      System.out.println(NewInstanceClass.newInstance(Pair.class));;
    }
}
