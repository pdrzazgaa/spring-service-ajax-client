package com.example.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.UnknownHostException;

@SpringBootApplication
public class RestServiceApplication {

    public static void main(String[] args) {
        try {
            MyData.myInfo();
        } catch (UnknownHostException e) {
            System.out.println("Nie znaleziono hosta");
        }

        SpringApplication.run(RestServiceApplication.class, args);
    }

}
