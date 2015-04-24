package com.corejava.collections;

import java.util.HashSet;

public class OnOffCollection<E> extends HashSet<E>{
    private static final long serialVersionUID = 198769579L;

    @Override
     public boolean add(E element) {
         if (element == null) {
             throw new NullPointerException("Adding a null element is not allowed");
         }
         if (this.contains(element)) {
             this.remove(element);
             return false;
         }
         super.add(element);
         return true;
     }
     
}
