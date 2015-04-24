package com.corejava.files.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.corejava.files.FileUtils;

public class FileUtilsTest {

private static final Path path1= Paths.get("file1.txt");
private static final Path path2= Paths.get("file2.txt");
private static final File aFile = new File("file2.txt");
final String content = String.format("Lorem ipsum dolor sit amet, consectetuer adipiscing elit,%n"
        + "Aenean commodo ligula eget dolor.Aenean massa.%n"
        + "Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.");

    @Before
    public void setUpReadFromPath() throws Exception {
        FileUtils.writeTo(path1, content);
        FileUtils.writeTo(aFile, content);
    }

    @After
    public void tearDown() throws Exception {
        Files.deleteIfExists(path1);
        Files.deleteIfExists(path2);
        
    }

    @Test
    public void readFromPathTest() {
        try {
            assertEquals(content, FileUtils.readFrom(path1) );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void readFromFileTest() {
        try {
            assertEquals(content, FileUtils.readFrom(aFile.toPath()) );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }  
}
