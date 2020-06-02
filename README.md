This is a little application that I developed in order to get to know about the Java 
framework Spring Boot.

This is a REST application, with GET, POST, UPDATE and DELETE methods. It uses a service 
to connect 
to the backend. This backend consist of a simple class, Person, with a few 
attributes.

The data can be stored through a DAO, using an in-memory storage, or a real database, 
such as Postgres, using db migrations (flywaydb) and Hikari (JDBC DataSource 
implementation that provides a connection pooling mechanism).

Basic integration tests done with Postman. 