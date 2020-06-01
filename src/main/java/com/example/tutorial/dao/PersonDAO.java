package com.example.tutorial.dao;

import com.example.tutorial.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDAO {

    List<Person> getAllPeople();

    Person insertPerson(UUID id, Person person);

    default Person insertPerson(Person person){
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    Optional<Person> getByID(UUID id);

    Person updatePerson(Person person);

    Person deletePerson(Person person);
}
