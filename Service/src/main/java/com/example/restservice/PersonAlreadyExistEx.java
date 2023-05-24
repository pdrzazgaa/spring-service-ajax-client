package com.example.restservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class PersonAlreadyExistEx extends Exception{
    public PersonAlreadyExistEx(String mess) {
        super(mess);
        System.out.println("Person Already Exists Exception");
    }
}