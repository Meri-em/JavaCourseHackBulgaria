package com.corejava.generics;
import java.util.ArrayList;
import java.util.List;


public class Favourites  {

    
    private List<Object> favourite;
    
    public Favourites(){
        favourite = new ArrayList<Object>(); 
    }
    
    public  <T> void add(Class<T> clazz, Object fav ){
        favourite.add((Object) clazz.cast(fav));
    }
    
    public <T> Object get(Class<T> clazz){
        Object obj = null;
        for(int i = 0; i < favourite.size(); i++){
            if(favourite.get(i).getClass().equals(clazz))
                obj = favourite.get(i);
                
        }
        return obj;
    }
    
}
