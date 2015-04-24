package com.corejava.collections;

import java.util.LinkedHashMap;
import java.util.Map;

public class CountWords {
    
    public static String printMessage(LinkedHashMap<String, Integer> map){
        StringBuilder result = new StringBuilder();
        result.append("{ ");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            result.append(entry.getKey() + ":" + entry.getValue() + ", ");
        }
        return result.toString().substring(0, result.length() - 2) + " }";
    }
    
    public static Map<String,Integer> countWords(String text){
        Map<String,Integer> map = new LinkedHashMap<>(); 
        String[] arrayText = text.split(" +");
        for(String element: arrayText){
            if (map.containsKey(element))
                map.put(element, map.get(element) + 1);
            else
                map.put(element, 1);
        }
        return map;
    }
    
 
 
}