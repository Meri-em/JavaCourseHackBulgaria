package com.corejava.remotebashinvocation;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class RBIProtocol {
    public static final int PORT = 6789;
    public static final String EOM = "<[EOM]>";  //end of message

    public static String readMessage(Socket s) throws UnknownHostException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String line;
        while (!(line = in.readLine()).equals(EOM)) {
            sb.append(line + System.lineSeparator());
        }

        sb.trimToSize();
        return sb.toString();
    }

    public static void writeMessage(Socket s, String message) throws UnknownHostException, IOException {
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        out.println(message);
    }

    //reads input from comandline
    public static String readInput() throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String line;

        do {
            line = stdIn.readLine();
            sb.append(line + System.lineSeparator());
        } while (!line.equals(EOM));

        sb.trimToSize();
        return sb.toString();
    }
    
}

