package com.example.restservice;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PersonController {

    PersonRepository personRepository = new PersonRepositoryImpl();
    @RequestMapping(value = "/persons/{id}", method = RequestMethod.GET)
    public EntityModel<Person> getPerson(@PathVariable int id) throws PersonNotFoundEx, BadRequestEx{
        System.out.println("...wywołano getPerson");
        Person p = personRepository.getPerson(id);
        return EntityModel.of(p,
                linkTo(methodOn(PersonController.class).getPerson(id)).withSelfRel(),
                linkTo(methodOn(PersonController.class).deletePerson(id)).withRel("delete"),
                linkTo(methodOn(PersonController.class).getPersons()).withRel("list all"));
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(p);
    }
    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public CollectionModel<EntityModel<Person>> getPersons(){
        System.out.println("...wywołano getPersons");

            List<EntityModel<Person>> list = personRepository.getAllPersons()
                    .stream().map(person ->
                            {
                                try {
                                    return EntityModel.of(person,
                                            linkTo(methodOn(PersonController.class).getPersons()).withRel("list all"),
                                            linkTo(methodOn(PersonController.class).deletePerson(person.getId())).withRel("delete"),
                                            linkTo(methodOn(PersonController.class).getPerson(person.getId())).withSelfRel());
                                } catch (PersonNotFoundEx PNFEx) {
                                    throw new RuntimeException(PNFEx);
                                } catch (BadRequestEx BDEx) {
                                    throw new RuntimeException(BDEx);
                                }
                            }).toList();
            return CollectionModel.of(list,
                    linkTo(methodOn(PersonController.class)
                            .getPersons()).withSelfRel());

//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(list);
    }
    @RequestMapping(value = "/persons", method = RequestMethod.POST)
    public EntityModel<Person> addPerson(@RequestBody Person person) throws BadRequestEx, PersonNotFoundEx{
        System.out.println("...wywołano addPerson");
        Person p = personRepository.addPerson(person);
        return EntityModel.of(p,
                linkTo(methodOn(PersonController.class).addPerson(p)).withSelfRel(),
                linkTo(methodOn(PersonController.class).updatePerson(p)).withRel("update"));
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .body(p);
    }
    @RequestMapping(value = "/persons/{id}", method = RequestMethod.PUT)
    public EntityModel<Person> updatePerson(@RequestBody Person person) throws PersonNotFoundEx, BadRequestEx{
        System.out.println("...wywołano updatePerson");
        Person p = personRepository.updatePerson(person);
        return EntityModel.of(p,
                linkTo(methodOn(PersonController.class).updatePerson(p)).withSelfRel(),
                linkTo(methodOn(PersonController.class).addPerson(p)).withRel("add"));
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .body(p);
    }
    @RequestMapping(value = "/persons/{id}", method = RequestMethod.DELETE)
    public EntityModel deletePerson(@PathVariable int id) throws PersonNotFoundEx, BadRequestEx{
        System.out.println("...wywołano deletePerson");
        boolean b = personRepository.deletePerson(id);
        return EntityModel.of(
                linkTo(methodOn(PersonController.class).deletePerson(id)).withSelfRel(),
                linkTo(methodOn(PersonController.class).getPerson(id)).withRel("get"),
                linkTo(methodOn(PersonController.class).getPersons()).withRel("list all"));
        //        return ResponseEntity.noContent().build();
    }
    @RequestMapping(value = "/persons/count", method = RequestMethod.GET)
    public EntityModel countPersons(){
        System.out.println("...wywołano countPerson");
        CountPerson c = new CountPerson(personRepository.countPersons());
        return EntityModel.of(c,
                linkTo(methodOn(PersonController.class).getPersons()).withSelfRel(),
                linkTo(methodOn(PersonController.class).getPersons()).withRel("list all"));

    }


}
