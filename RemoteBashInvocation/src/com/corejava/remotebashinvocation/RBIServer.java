package com.corejava.remotebashinvocation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RBIServer {

    public static void main(String[] args) {

        int portNumber = RBIProtocol.PORT;
        try (ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        ) {
            String command = RBIProtocol.readMessage(clientSocket);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
            try {

                String start = sdf.format(new Date());

                Process process = Runtime.getRuntime().exec(command);
                String end = sdf.format(new Date());
                System.out.println("Command: " + command.trim() + System.lineSeparator() + "Started " + start
                        + System.lineSeparator() + "Finished " + end);
                BufferedReader processOutputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line = null;
                while ((line = processOutputReader.readLine()) != null) {
                    out.println(line);
                }

            } catch (IOException e) {
                out.println("The command is invalid");
            }
            out.println(RBIProtocol.EOM);

        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port " + portNumber
                    + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
