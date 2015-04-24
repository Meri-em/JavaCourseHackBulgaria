package com.corejava.networking;

import java.io.IOException;
import java.net.InetAddress;

public class IPsInMyNetwork {
    public static void main(String[] args) {

        int timeout = 1000;
        for (int i = 1; i < 254; i++) {
            System.out.println(i);
            String subnet = "192.168.1";
            String host = subnet + "." + i;
            try {
                if (InetAddress.getByName(host).isReachable(timeout)) {
                    System.out.println(host + " is reachable");
                } else {
                    System.out.println(host + " is not reachable");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
