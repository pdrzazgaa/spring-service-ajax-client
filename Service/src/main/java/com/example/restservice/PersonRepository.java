package com.example.restservice;

import java.util.List;

public interface PersonRepository {
    List<Person> getAllPersons();
    Person getPerson(int id) throws PersonNotFoundEx;
    Person updatePerson(int personID, PersonDTO person) throws PersonNotFoundEx;
    boolean deletePerson(int id) throws PersonNotFoundEx;
    Person addPerson(Person person) throws BadRequestEx;
    Integer countPersons();

}
