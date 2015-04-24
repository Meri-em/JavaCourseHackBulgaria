package com.corejava.pair;


public final class Pair {
    private final Object first;
    private final Object second;
    
    public Pair(Object first, Object second){
        this.first = first;
        this.second = second;
    }
    
    public Object getFirst(){
        return this.first;
    }
    
    public Object getSecond(){
        return this.second;
    }
    
    public String toString(){
        return String.format("Pair with first element {} and second element {}", this.first, this.second);
    }
    
     public boolean equals(Pair other) {
         return this.first.equals(other.first) && this.second.equals(other.second);
     }
}

