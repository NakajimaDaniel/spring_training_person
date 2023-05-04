package com.example.spring_training_person;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class PersonMapper {
  public PersonVO2 convertEntityToVo(Person person) {
    PersonVO2 vo = new PersonVO2();

    vo.setId(person.getId());
    vo.setAddress(person.getAddress());
    vo.setBirthDate(new Date());
    vo.setFirstName(person.getFirstName());
    vo.setLastName(person.getLastName());
    vo.setGender(person.getGender());
    return vo;
  }

  public Person convertVoToEntity(PersonVO2 vo) {
    Person entity = new Person();

    entity.setId(vo.getId());
    entity.setAddress(vo.getAddress());
    entity.setFirstName(vo.getFirstName());
    entity.setLastName(vo.getLastName());
    entity.setGender(vo.getGender());
    return entity;
  }

}
