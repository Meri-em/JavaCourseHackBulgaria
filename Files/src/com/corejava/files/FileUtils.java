package com.corejava.files;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

//not intended for reading and writing in large files.
public class FileUtils {
    
    private FileUtils(){
        
    }

    public static String readFrom(Path path) throws IOException{
       return new String(Files.readAllBytes(path));
    }
    
   
    public static String readFrom(File file) throws IOException {
        Path path = file.toPath();
        return readFrom(path);
    }
    
    public static void writeTo(Path path, String content) throws IOException{
        Files.write(path, content.getBytes(), StandardOpenOption.CREATE);
    }
    
 public static void writeTo(File file, String content) throws IOException{
         Path path = file.toPath();
         writeTo(path, content);
    }
}
