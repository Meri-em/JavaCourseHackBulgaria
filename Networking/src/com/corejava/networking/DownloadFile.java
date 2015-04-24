package com.corejava.networking;

import java.net.URL;
import java.io.*;

public class DownloadFile {

    public static void main(String[] args) {
        String fileName = "image.jpg";
        String urlString = "http://pixabay.com/static/uploads/photo/2014/02/03/02/38/rose-257098_640.jpg";
        final byte data[] = new byte[1024];
        try (FileOutputStream fout = new FileOutputStream(fileName);
                BufferedInputStream in = new BufferedInputStream(new URL(urlString).openStream());) {
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {
                fout.write(data, 0, count);
            }
        } catch (IOException e) {
            System.out.println("FileNotFound");
        }

    }
}
