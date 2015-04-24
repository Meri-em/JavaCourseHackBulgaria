package com.corejava.files.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.corejava.files.WordCountInFile;

public class WordCountInFileTest {
    Path path1 = Paths.get("testfile.txt");

    @Before
    public void setUp() throws Exception {
        String lorem = String.format("Lorem ipsum dolor sit amet, consectetuer adipiscing elit,%n"
                + "sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.%n");
        path1.toFile().createNewFile();
        Files.write(path1, lorem.getBytes(Charset.defaultCharset()), StandardOpenOption.WRITE);
    }

    @After
    public void tearDown() throws Exception {
        Files.deleteIfExists(path1);
    }

    @Test
    public void parseRowsTest(){
        try {
            assertEquals(2, WordCountInFile.wordCount(path1).getLineCount());
            assertEquals(21, WordCountInFile.wordCount(path1).getWordCount());
            assertEquals(143, WordCountInFile.wordCount(path1).getCharacterCount());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    
    

}