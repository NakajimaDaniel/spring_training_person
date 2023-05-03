package com.example.spring_training_person;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

@Service
public class PersonServices {
  private final AtomicLong counter = new AtomicLong();

  private Logger logger = Logger.getLogger(PersonServices.class.getName());

  public List<Person> findAll() {
    List<Person> persons = new ArrayList<>();
    for (int i = 0; i < 8; i++) {
      Person person = mockPerson(i);
      persons.add(person);
    }
    return persons;
  }

  public Person findById(String id) {

    logger.info("finding one person");
    Person person = new Person();

    person.setId(counter.incrementAndGet());
    person.setFirstName("sdsadas");
    person.setLastName("sdsadas");
    person.setAdress("sdsadas");
    person.setGender("sdsadas");

    return person;
  }

  public Person create(Person person) {
    return person;
  }

  public Person update(Person person) {
    return person;
  }

  public void delete(String id) {

  }

  private Person mockPerson(int i) {
    logger.info("finding one person");
    Person person = new Person();

    person.setId(counter.incrementAndGet());
    person.setFirstName("sdsadas" + i);
    person.setLastName("sdsadas" + i);
    person.setAdress("sdsadas" + i);
    person.setGender("sdsadas" + i);

    return person;
  }

}
