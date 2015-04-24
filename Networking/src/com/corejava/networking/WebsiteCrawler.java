package com.corejava.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebsiteCrawler {
    private String url;
    private String needle;
    
    public WebsiteCrawler(String url, String needle){
        this.url = url;
        this.needle = needle;
    }
    
    public void findNeedle() throws MalformedURLException, IOException, URISyntaxException{
        if(searchNeedle(url) != null){
            System.out.println("jfh");
        }
        else{
            String content = getURLContent(new URL(url));
            List<String> list = getAllLinks(content);
            boolean notFound = true;
            for(String aURL: list){
                String link = makeAbsoluteURL(aURL);
                if(searchNeedle(link) != null){
                    System.out.println(link);
                    notFound = false;
                }
            }
            if(notFound)
                System.out.println(needle + " is not found!");
        }
    }
    
    private String getURLContent(URL aURL) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(aURL.openStream(), StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = in.readLine()) != null){
            sb.append(line + System.lineSeparator());
        }
        return sb.toString();
    }
    
    private  List<String> getAllLinks(String content) {
        ArrayList<String> resultList = new ArrayList<>();
        String regex = "<a.*?href=\"((?!javascript).*?)\".*?>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            String aURL = matcher.group(1).replaceAll("\\.\\.", "").replaceAll("^/+", "");
            if(!resultList.contains(aURL))
                resultList.add(aURL);
        }
        return resultList;
    }
    
    private String searchNeedle(String absURL) throws MalformedURLException, IOException{
        String content = getURLContent(new URL(absURL));
        if(content.toLowerCase().contains(needle.toLowerCase())){
            return absURL;
        }
        return null;
    }
    
    private String makeAbsoluteURL(String aURL) throws  URISyntaxException{
        URI link = new URI(aURL).normalize();
        if(!link.isAbsolute()){
            return  url + "/" + link.toString(); 
        }
        return link.toString();
    }
    
}
