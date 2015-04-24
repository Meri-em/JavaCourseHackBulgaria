package com.corejava.files;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FixEncoding {
    static final Path target = Paths.get("result.txt");
    
    public static void fixEncoding(Path path) {
        try(BufferedReader in = Files.newBufferedReader(path, Charset.defaultCharset());
        BufferedWriter out = Files.newBufferedWriter(target, Charset.forName("Windows-1251"), StandardOpenOption.CREATE)){
           String line = null;
            while((line = in.readLine()) != null){
                out.write(line);
                out.newLine();
            }
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       
    }
    
    public static void main(String[] args) {
        Path path = Paths.get("lost.s04e11.hdtv.xvid-2hd.srt");
        fixEncoding(path);
    }
}