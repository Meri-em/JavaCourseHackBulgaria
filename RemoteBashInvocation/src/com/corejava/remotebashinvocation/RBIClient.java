package com.corejava.remotebashinvocation;

import java.io.IOException;
import java.net.Socket;
//import java.io.*;
//import java.net.*;
import java.net.UnknownHostException;

public class RBIClient {
  public static void main(String[] args) {
      if (args.length != 1) {
          System.err.println(
              "Insert server IP/host name as an argument");
          System.exit(1);
      }        
      String hostName = args[0];
      try(
              Socket client = new Socket(hostName, RBIProtocol.PORT);
              ){
          String msg = RBIProtocol.readInput();
          RBIProtocol.writeMessage(client, msg); 
          System.out.println(RBIProtocol.readMessage(client));
            
      }
      catch (UnknownHostException e) {
          System.err.println("Don't know about host " + hostName);
          System.exit(1);
      } catch (IOException e) {
          System.err.println("Couldn't get I/O for the connection to " +
              hostName);
          System.exit(1);
      } 
      
  }

}

