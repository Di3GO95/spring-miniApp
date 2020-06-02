package com.example.tutorial.dao;

import com.example.tutorial.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("PostgresDAO")
public class PostgresDAO implements PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostgresDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Person> getAllPeople() {
        final String sql = "SELECT id, name FROM person";
        final List<Person> personList = jdbcTemplate.query(sql, (resultSet, i) -> {
            final UUID id = UUID.fromString(resultSet.getString("id"));
            final String name = resultSet.getString("name");
            return new Person(id, name);
        });
        return personList;
    }

    @Override
    public Person insertPerson(UUID id, Person person) {
        final String sql = "INSERT INTO person (id, name) VALUES (?, ?)";
        jdbcTemplate.update(sql, id, person.getName());

        Person p = new Person(id, person.getName());
        return null;
    }

    @Override
    public Optional<Person> getByID(UUID id) {
        final String sql = "SELECT id, name FROM person WHERE id = ?";

        Person person = jdbcTemplate.queryForObject(sql, new Object[]{id},
                (resultSet, i) -> {
                    final UUID personUUID = UUID.fromString(resultSet.getString("id"));
                    final String name = resultSet.getString("name");
                    return new Person(personUUID, name);
                });
        return Optional.ofNullable(person);
    }

    @Override
    public Person updatePerson(Person person) {
        Optional<Person> personOrNot = getByID(person.getId());

        if (personOrNot.isPresent()){
            final String sql = "UPDATE person SET name = ? WHERE id = ?";
            Object[] args = new Object[] {person.getName(), person.getId()};

            int rowsAffected = jdbcTemplate.update(sql, args);
            if (rowsAffected == 1){
                return person;
            }
        }
        return null;
    }

    @Override
    public Person deletePerson(UUID id) {
        Optional<Person> personOrNot = getByID(id);

        if (personOrNot.isPresent()){
            final String sql = "DELETE FROM person WHERE id = ?";
            Object[] args = new Object[] {id};

            int rowsAffected = jdbcTemplate.update(sql, args);
            if (rowsAffected == 1){
                return personOrNot.get();
            }
        }
        return null;
    }
}
