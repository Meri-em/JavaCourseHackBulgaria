package com.corejava.collections;

import java.util.Set;
import java.util.HashSet;

public class FindDuplicates {
    public <T> Set<T> findDuplicateElements(Set<T>... sets) {
        Set<T> result = new HashSet<>();
        if (sets.length == 0) {
            return result;
        }
        for (T element: sets[0]) {
            boolean isInAllSets = true;
            for (Set<T> set: sets) {
                if (!set.contains(element)) {
                    isInAllSets = false;
                    break;
                }
            }
            if (isInAllSets) {
                result.add(element);
            }
        }
        
        return result;
    }

}
