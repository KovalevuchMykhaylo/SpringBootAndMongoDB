package com.mongo.mongocrud.controller;

import com.mongo.mongocrud.entity.Person;
import com.mongo.mongocrud.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class IndexRestController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/all")
    public Flux<Person> allPersons(){
        return personRepository.findAll();
    }

    @PostMapping("/add")
    public Mono<Person> createTweets(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> deletePerson (@PathVariable(value = "id") String id){
        return personRepository.findById(id)
                .flatMap(existingTweet ->
                        personRepository.delete(existingTweet)
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
