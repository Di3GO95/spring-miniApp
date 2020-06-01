package com.example.tutorial.api;

import com.example.tutorial.model.Person;
import com.example.tutorial.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("person")
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(path = "{id}")
    public Person getById(@PathVariable("id") UUID id){
        return personService.getById(id)
                .orElse(null);
    }

    @GetMapping
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }

    @PostMapping
    public Person addPerson (@Valid @NonNull @RequestBody Person person){
        return personService.insertPerson(person);
    }

    @PutMapping
    public Person update(@NonNull @RequestBody Person person){
        System.out.println(person.getId());
        return personService.update(person);
    }

    @DeleteMapping
    public Person delete(@NonNull @RequestBody Person person){
        return personService.delete(person);
    }
}
