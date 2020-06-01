package com.example.tutorial.dao;

import com.example.tutorial.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDAO")
public class FakePersonDataAccess implements PersonDAO {

    private static List<Person> DB = new ArrayList<>();

    @Override
    public List<Person> getAllPeople() {
        return DB;
    }

    @Override
    public Person insertPerson(UUID id, Person person) {
        Person p = new Person(id, person.getName());
        DB.add(p);
        return p;
    }

    @Override
    public Optional<Person> getByID(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public Person updatePerson(Person person) {
        return getByID(person.getId())
                .map(p -> {
                    int indexOfPersonToDelete = DB.indexOf(p);
                    if (indexOfPersonToDelete >= 0){
                        DB.set(indexOfPersonToDelete, person);
                        return person;
                    }
                    return null;
                })
                .orElse(null);
    }

    @Override
    public Person deletePerson(Person person) {
        Optional<Person> personFound = getByID(person.getId());
        if (personFound.isPresent()){
            Person personGet = personFound.get();
            DB.remove(personGet);
            return personGet;
        }
        return null;
    }


}
