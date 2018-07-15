package com.mongo.mongocrud.repository;

import com.mongo.mongocrud.entity.Person;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PersonRepository extends ReactiveCrudRepository<Person, String> {
}
