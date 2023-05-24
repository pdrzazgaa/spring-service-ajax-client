package com.example.restservice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/persons",
        produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE})
@RestController
public class PersonController {

    PersonRepository personRepository = new PersonRepositoryImpl();
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Person> getPerson(@PathVariable int id) throws PersonNotFoundEx{
        System.out.println("...wywołano getPerson");
        Person p = personRepository.getPerson(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(p);
    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Person>> getPersons(
            @RequestParam(required = false) Optional<String> name,
            @RequestParam(required = false) Optional<String> email,
            @RequestParam(required = false) Optional<Integer> age){
        System.out.println("...wywołano getPersons");
        List<Person> list;
        if (name.isEmpty() && email.isEmpty() && age.isEmpty())
            list = personRepository.getAllPersons();
        else
            list = personRepository.getPersonsFilter(name, email, age);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Person> addPerson(@RequestBody PersonDTO person) throws BadRequestEx, PersonAlreadyExistEx{
        System.out.println("...wywołano addPerson");
        Person p = personRepository.addPerson(person);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(p);
    }

    @RequestMapping(value = "/{personID}", method = RequestMethod.PUT)
    public ResponseEntity<Person> updatePerson(@PathVariable int personID, @RequestBody PersonDTO person)
            throws PersonNotFoundEx, BadRequestEx{
        System.out.println("...wywołano updatePerson");
        Person p = personRepository.updatePerson(personID, person);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(p);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deletePerson(@PathVariable int id) throws PersonNotFoundEx{
        System.out.println("...wywołano deletePerson");
        boolean b = personRepository.deletePerson(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("{\"val\": \"OK\"}");
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ResponseEntity<CountPerson> countPersons(){
        System.out.println("...wywołano countPerson");
        CountPerson c = new CountPerson(personRepository.countPersons());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(c);
    }
}
