package com.corejava.generics;

public class Main {
    public static void main(String[] args) {
        ClassInfo classInfo = AnnotatedClass.class.getAnnotation(ClassInfo.class);
        AnnotatedClass annotatedClass = new AnnotatedClass("Darina", 11);
        System.out.println(classInfo.author());
        System.out.println(classInfo.checked());
        System.out.println(classInfo.relatedClasses());
        System.out.println(annotatedClass.getClass().getAnnotation(ClassInfo.class));
    }
    
   
    
    
}
