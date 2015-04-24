package com.corejava.files;

import java.nio.file.Paths;

public class ReduceFilePath {
    public static String reduceFilePath(String path){
        return Paths.get(path).normalize().toString();
    }
}
