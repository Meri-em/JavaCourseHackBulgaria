package com.corejava.generics;
public class NewInstanceClass {
    public static <T> T newInstance(Class<T> clazz){
       try {
        return  clazz.newInstance();
    } catch (InstantiationException e) {
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    }
       return null;
    }
}
