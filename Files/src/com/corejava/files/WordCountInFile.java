package com.corejava.files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WordCountInFile {
    public static WordCountResult wordCount(Path path) throws IOException {
        List<String> list = Files.readAllLines(path, Charset.defaultCharset());
        int lines = list.size();
        int words = 0;
        int characters = 0;
        for(String row: list){
            String[] wordsInRow = row.split("\\s");
            words += wordsInRow.length;
            characters += row.length();
        }
        return new WordCountResult(lines, words, characters);
    }
    
    public static WordCountResult wordCount(File file) throws IOException{
        return wordCount(file.toPath());
    }
}
