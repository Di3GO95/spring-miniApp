package com.example.tutorial.service;

import com.example.tutorial.dao.PersonDAO;
import com.example.tutorial.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonDAO personDAO;

    @Autowired
    public PersonService(@Qualifier("PostgresDAO") PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public Optional<Person> getById(UUID id){
        return personDAO.getByID(id);
    }

    public List<Person> getAllPeople(){
        return this.personDAO.getAllPeople();
    }

    public Person insertPerson(Person person){
        return this.personDAO.insertPerson(person);
    }

    public Person update(Person person){
        return this.personDAO.updatePerson(person);
    }

    public Person delete(UUID id){
        return this.personDAO.deletePerson(id);
    }
}
