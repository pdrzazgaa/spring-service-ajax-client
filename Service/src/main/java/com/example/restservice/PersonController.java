package com.example.restservice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    PersonRepository personRepository = new PersonRepositoryImpl();
    @RequestMapping(value = "/persons/{id}", method = RequestMethod.GET)
    public ResponseEntity<Person> getPerson(@PathVariable int id) throws PersonNotFoundEx{
        System.out.println("...wywołano getPerson");
        Person p = personRepository.getPerson(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(p);
    }
    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> getPersons(){
        System.out.println("...wywołano getPersons");
        List<Person> list = personRepository.getAllPersons();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }

    @RequestMapping(value = "/persons", method = RequestMethod.POST)
    public ResponseEntity<Person> addPerson(@RequestBody Person person) throws BadRequestEx{
        System.out.println("...wywołano addPerson");
        Person p = personRepository.addPerson(person);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(p);
    }

    @RequestMapping(value = "/persons/{personID}", method = RequestMethod.PUT)
    public ResponseEntity<Person> updatePerson(@PathVariable int personID, @RequestBody PersonDTO person)
            throws PersonNotFoundEx{
        System.out.println("...wywołano updatePerson");
        Person p = personRepository.updatePerson(personID, person);
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(p);
    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deletePerson(@PathVariable int id) throws PersonNotFoundEx{
        System.out.println("...wywołano deletePerson");
        boolean b = personRepository.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/persons/count", method = RequestMethod.GET)
    public ResponseEntity<CountPerson> countPersons(){
        System.out.println("...wywołano countPerson");
        CountPerson c = new CountPerson(personRepository.countPersons());
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(c);
    }
}
