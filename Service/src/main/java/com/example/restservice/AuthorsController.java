package com.example.restservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RequestMapping(produces = {
                MediaType.APPLICATION_JSON_VALUE})
@RestController
public class AuthorsController {
    @RequestMapping(value="authors")
    public ResponseEntity<Author> getAuthors(){
        Author authors = new Author("Czerwiński Jakub 260335, Drzazga Paulina 260370");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authors);
    }
}
