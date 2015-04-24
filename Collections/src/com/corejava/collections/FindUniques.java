package com.corejava.collections;

import java.util.LinkedHashMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class FindUniques {
    public <T> T fistFirstUniqueElement(Collection<T> col) {
        LinkedHashMap<T, Integer> map = new LinkedHashMap<>();
        for (T element : col) {
            if (map.containsKey(element)) {
                map.put(element, map.get(element) + 1);
            } else {
                map.put(element, 0);
            }
        }
        Set<Map.Entry<T, Integer>>  entrySet = map.entrySet();
        for (Map.Entry<T, Integer> entry: entrySet) {
            if (entry.getValue() == 0) {
                return entry.getKey();
            }
        
        }
        throw new NullPointerException();
    }
}
