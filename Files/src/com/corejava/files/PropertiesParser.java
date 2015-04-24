package com.corejava.files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PropertiesParser {
    public static Map<String, String> parseRows(List<String> list) {
        Map<String, String> map = new HashMap<>();
        Pattern pattern = Pattern.compile("^[^#\\S]*([^\\s=]*)[\\s]*=\\s*(.*)\\s*");
        for (String row : list) {
            Matcher matcher = pattern.matcher(row);
            if(matcher.find())
                map.put(matcher.group(1), matcher.group(2));
        }

        return map;
    }
    
    public static Map<String, String> parseProperties(File file) throws IOException{
        List<String> list = Files.readAllLines(file.toPath(), Charset.defaultCharset());
        return parseRows(list);
    }
}
