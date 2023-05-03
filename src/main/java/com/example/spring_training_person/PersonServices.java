package com.example.spring_training_person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServices {
  // private final AtomicLong counter = new AtomicLong();

  // private Logger logger = Logger.getLogger(PersonServices.class.getName());

  @Autowired
  PersonRepository repository;

  public List<Person> findAll() {
    return repository.findAll();
  }

  public Person findById(Long id) {

    return repository.findById(id)
        .orElseThrow(null);
  }

  public Person create(Person person) {
    return repository.save(person);
  }

  public Person update(Person person) {

    Person entity = repository.findById(person.getId())
        .orElseThrow(null);

    entity.setFirstName(person.getFirstName());
    entity.setLastName(person.getLastName());
    entity.setAddress(person.getAddress());
    entity.setGender(person.getGender());

    return repository.save(person);
  }

  public void delete(Long id) {
    Person entity = repository.findById(id)
        .orElseThrow(null);

    repository.delete(entity);
  }

}
