package com.corejava.networking;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;


public class WebsiteCrawlerMain {
    public static void main(String[] args) throws MalformedURLException, IOException, URISyntaxException {
        WebsiteCrawler w = new WebsiteCrawler( "http://ebusiness.free.bg", "Револвираща");
        w.findNeedle();
    }
}
