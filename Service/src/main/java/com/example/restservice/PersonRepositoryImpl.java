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
    public Person updatePerson(int personID, PersonDTO person) throws PersonNotFoundEx, BadRequestEx {
        if (person.getName().isEmpty()  || person.getAge() <= 0 || person.getEmail().isEmpty())
            throw new BadRequestEx("Invalid input. No content.");
        for (Person p:personList){
            if (p.getId() == personID){
                p.setAge(person.getAge());
                p.setName(person.getName());
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
    public Person addPerson(PersonDTO person)  throws BadRequestEx{
        if (person.getName().isEmpty()  || person.getAge() <= 0 || person.getEmail().isEmpty())
            throw new BadRequestEx("Invalid input. No content.");
        Person newPerson = new Person(idCounter++, person.getName(), person.getAge(), person.getEmail());
        personList.add(newPerson);
        return newPerson;
    }
    @Override
    public Integer countPersons(){
        return personList.size();
    }

}
