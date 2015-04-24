package com.corejava.generics;

@ClassInfo(
        author = "Maria", relatedClasses = { BoxExample.class  }
        )
public class AnnotatedClass {
    private String name;
    private int id;
    
    public AnnotatedClass(String name,int id){
        this.name = name;
        this.id = id;
    }
}
