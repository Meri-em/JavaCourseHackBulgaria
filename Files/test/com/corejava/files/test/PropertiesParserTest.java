package com.corejava.files.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.corejava.files.PropertiesParser;

public class PropertiesParserTest {
    final String prop = String.format("# this=comment%na1=b1%na2 =b2%na3    =    b3%n   a4 = b4%na5=b6=b7=b8%n" +
            "a6=b9 #comment%na7==b10"); 
    private static final File propFile = new File("prop.properties");

    @Before
    public void setUp() throws Exception {
        Path filePath2 = propFile.toPath();
        Files.write(filePath2, prop.getBytes(Charset.defaultCharset()), StandardOpenOption.CREATE);
    }

    @After
    public void tearDown() throws Exception {
        Files.deleteIfExists(propFile.toPath());
    }

    @Test
    public void parseRowsTest(){
        List<String> list = new ArrayList<>();
        list.add("# this=comment");
        list.add("a1=b1");
        list.add("a2 =b2");
        list.add("a3    =    b3");
        list.add("   a4 = b4");
        list.add("a5=b6=b7=b8");
        list.add("a6=b9 #comment");
        list.add("a7==b10");
        Map<String, String> map = new HashMap<>();
        map.put("a1", "b1");
        map.put("a2", "b2");
        map.put("a3", "b3");
        map.put("a4", "b4");
        map.put("a5", "b6=b7=b8");
        map.put("a6", "b9 #comment");
        map.put("a7", "=b10");
        assertEquals(map, PropertiesParser.parseRows(list));
        
    }
    
    @Test
    public void parsePropertiesTest() throws IOException{
        Map<String, String> map = new HashMap<>();
        map.put("a1", "b1");
        map.put("a2", "b2");
        map.put("a3", "b3");
        map.put("a4", "b4");
        map.put("a5", "b6=b7=b8");
        map.put("a6", "b9 #comment");
        map.put("a7", "=b10");
        assertEquals(map, PropertiesParser.parseProperties(propFile));
    }
    
    

}
