package com.corejava.files.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.corejava.files.ReduceFilePath;

public class ReduceFilePathTest {
    @Test
    public void reduceFilePathTest(){
        assertEquals("\\",ReduceFilePath.reduceFilePath("/"));
        assertEquals("\\",ReduceFilePath.reduceFilePath("/srv/../"));
        assertEquals("\\srv\\www\\htdocs\\wtf",ReduceFilePath.reduceFilePath("/srv/www/htdocs/wtf/"));
        assertEquals("\\srv\\www\\htdocs\\wtf",ReduceFilePath.reduceFilePath("/srv/www/htdocs/wtf"));
        assertEquals("\\srv",ReduceFilePath.reduceFilePath("/srv/./././././"));
        assertEquals("\\etc\\wtf",ReduceFilePath.reduceFilePath("/etc//wtf/"));
        assertEquals("\\",ReduceFilePath.reduceFilePath("/etc/../etc/../etc/../"));
        assertEquals("\\",ReduceFilePath.reduceFilePath("/../"));
    }
}
