package com.example.restservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class PersonRepositoryImpl implements PersonRepository{
    private final List<Person> personList;
    private int idCounter = 5;

    public PersonRepositoryImpl(){
        personList = new ArrayList<>();
        personList.add(new Person(1, "Paula", 21, "paudrza@gmail.com"));
        personList.add(new Person(2, "Kuba", 21, "kubacz@op.pl"));
        personList.add(new Person(3, "Ania", 16, "aniakrak@wp.pl"));
        personList.add(new Person(4, "Paweł", 29, "pawelpawel@gmail.com"));
    }

    @Override
    public List<Person> getAllPersons() {
        return personList;
    }

    @Override
    public List<Person> getPersonsFilter(Optional<String> name, Optional<String> email, Optional<Integer> age){
        return personList.stream()
                .filter(p -> age.isEmpty() || p.getAge() == age.get())
                .filter(p -> email.isEmpty() || Objects.equals(p.getEmail(), email.get()))
                .filter(p -> name.isEmpty() || Objects.equals(p.getName(), name.get()))
                .collect(Collectors.toList());
    }
    @Override
    public Person getPerson(int id) throws PersonNotFoundEx {
        for (Person p:personList){
            if (p.getId() == id)
                return p;
        }
        throw new PersonNotFoundEx(id);
    }

    @Override
    public Person updatePerson(int personID, PersonDTO person) throws PersonNotFoundEx {
        for (Person p:personList){
            if (p.getId() == personID){
                if (person.getAge() > 0)
                    p.setAge(person.getAge());
                if (person.getName() != null)
                    p.setName(person.getName());
                if (person.getEmail() != null)
                    p.setEmail(person.getEmail());
                return p;
            }
        }
        throw new PersonNotFoundEx(personID);
    }

    @Override
    public boolean deletePerson(int id) throws PersonNotFoundEx {
        for (Person p:personList){
            if (p.getId() == id) {
                personList.remove(p);
                return true;
            }
        }
        throw new PersonNotFoundEx(id);
    }

    @Override
    public Person addPerson(PersonDTO person) throws BadRequestEx, PersonAlreadyExistEx{
        checkInputs(person.getName(), person.getEmail(), person.getAge());
        for (Person p:personList){
            if (p.getEmail().equals(person.getEmail())) throw new PersonAlreadyExistEx("Person already exists.");
        }
        Person newPerson = new Person(idCounter++, person.getName(), person.getAge(), person.getEmail());
        personList.add(newPerson);
        return newPerson;
    }
    @Override
    public Integer countPersons(){
        return personList.size();
    }

    private void checkInputs(String name, String email, int age)  throws BadRequestEx{
        if (name == null || name.isEmpty() || name.isBlank())
            throw new BadRequestEx("Invalid input. No name content.");
        if (email == null || email.isEmpty() || email.isBlank())
            throw new BadRequestEx("Invalid input. No email content.");
        if (age <=0)
            throw new BadRequestEx("Invalid age input.");
    }
}
