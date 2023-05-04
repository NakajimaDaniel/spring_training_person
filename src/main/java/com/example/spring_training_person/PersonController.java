package com.example.spring_training_person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

  @Autowired
  private PersonServices service;

  @GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
  public List<PersonVO> findAll() {
    return service.findAll();
  }

  @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
      MediaType.APPLICATION_YML })
  public PersonVO findById(Long id) {
    return service.findById(id);
  }

  @PostMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
      MediaType.APPLICATION_YML }, produces = {
          MediaType.APPLICATION_JSON, com.example.spring_training_person.MediaType.APPLICATION_XML,
          MediaType.APPLICATION_YML })
  public PersonVO create(@RequestBody PersonVO person) {
    return service.create(person);
  }

  @PostMapping(value = "/v2", consumes = { MediaType.APPLICATION_JSON,
      MediaType.APPLICATION_XML, MediaType.APPLICATION_YML }, produces = { MediaType.APPLICATION_JSON,
          MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
  public PersonVO2 createV2(@RequestBody PersonVO2 person) {
    return service.createV2(person);
  }

  @PutMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
      MediaType.APPLICATION_YML }, produces = {
          MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
  public PersonVO update(@RequestBody PersonVO person) {
    return service.create(person);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

}
