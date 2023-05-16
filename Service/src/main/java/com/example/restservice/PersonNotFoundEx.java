package com.example.restservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PersonNotFoundEx extends Exception{
    public PersonNotFoundEx(int id) {
        super(String.valueOf(id));
        System.out.println("Person Not Found Exception. ID: " + id);
    }
}
