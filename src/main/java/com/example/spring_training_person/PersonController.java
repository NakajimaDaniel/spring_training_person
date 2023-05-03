package com.example.spring_training_person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

  @Autowired
  private PersonServices service;

  @GetMapping
  public Iterable<Person> findAll() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public Person findById(String id) {
    return service.findById(id);
  }

  @PostMapping
  public Person create(@RequestBody Person person) {
    return service.create(person);
  }

  @PutMapping
  public Person update(@RequestBody Person person) {
    return service.create(person);
  }

  @DeleteMapping("/{id}")
  public void delete(String id) {
    service.delete(id);
  }

}
