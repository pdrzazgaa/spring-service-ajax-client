package com.example.restservice;

import java.util.ArrayList;
import java.util.List;

public class PersonRepositoryImpl implements PersonRepository{
    private final List<Person> personList;

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
    public Person addPerson(Person person) throws BadRequestEx {
        for (Person p:personList){
            if (p.getId() == person.getId())
                throw new BadRequestEx(person.getId());
        }
        personList.add(person);
        return person;
    }
    @Override
    public Integer countPersons(){
        return personList.size();
    }

}
