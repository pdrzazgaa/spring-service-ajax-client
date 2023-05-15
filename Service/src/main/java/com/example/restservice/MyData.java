package com.example.restservice;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


class MyData {
    public static void myInfo() throws UnknownHostException {
        System.out.println("Jakub Czerwiński, 260335");
        System.out.println("Paulina Drzazga, 260370");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.format(DateTimeFormatter.ofPattern("dd-MMMM HH:mm")));
        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("os.name"));
        try {
            System.out.println("IP: " + Inet4Address.getLocalHost().getHostAddress());
        } catch(Exception e) {
            //do nothing
        }
        System.out.println("Witaj w świecie Javy 😃");
    }

}
