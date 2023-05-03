package com.example.spring_training_person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServices {

  @Autowired
  PersonRepository repository;

  public List<PersonVO> findAll() {
    return DozerMapper.parseListObject(repository.findAll(), PersonVO.class);
  }

  public PersonVO findById(Long id) {

    var entity = repository.findById(id)
        .orElseThrow(null);
    return DozerMapper.parseObject(entity, PersonVO.class);
  }

  public PersonVO create(PersonVO person) {

    var entity = DozerMapper.parseObject(person, Person.class);

    var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);

    return vo;
  }

  public PersonVO update(PersonVO person) {

    var entity = repository.findById(person.getId())
        .orElseThrow(null);

    entity.setFirstName(person.getFirstName());
    entity.setLastName(person.getLastName());
    entity.setAddress(person.getAddress());
    entity.setGender(person.getGender());

    var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);

    return vo;
  }

  public void delete(Long id) {
    var entity = repository.findById(id)
        .orElseThrow(null);

    repository.delete(entity);
  }

}
