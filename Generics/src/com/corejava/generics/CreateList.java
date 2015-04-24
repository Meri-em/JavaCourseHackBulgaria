package com.corejava.generics;
import java.util.ArrayList;
import java.util.List;


public class CreateList {
    public static <T> List<T> asList(T ... elements){
        List<T> result = new ArrayList<>();
        for(T e: elements){
            result.add(e);
        }
        return result;
    }
    
    public static void main(String[] args) {
        List<?> whatIsThis =asList(null,null,null);
        List<Object> hahaaah = asList(null,null,null);
        List<Integer> didNotExpectThisWTF = asList(null,null,null);
        List<Number> nowWhat = CreateList.<Number> asList(null,null,null);
        System.out.println(whatIsThis);
        System.out.println(hahaaah);
        System.out.println(didNotExpectThisWTF);
        System.out.println(nowWhat);
    }
}
