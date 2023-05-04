package com.example.spring_training_person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Service
public class PersonServices {

  @Autowired
  PersonRepository repository;

  @Autowired
  PersonMapper mapper;

  public List<PersonVO> findAll() {
    var persons = DozerMapper.parseListObject(repository.findAll(), PersonVO.class);
    persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
    return persons;
  }

  public PersonVO findById(Long id) {

    var entity = repository.findById(id)
        .orElseThrow(null);
    var vo = DozerMapper.parseObject(entity, PersonVO.class);
    vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
    return vo;
  }

  public PersonVO create(PersonVO person) {

    var entity = DozerMapper.parseObject(person, Person.class);

    var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
    vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());

    return vo;
  }
  /*
   * public PersonVO2 createV2(PersonVO2 person) {
   * 
   * var entity = mapper.convertVoToEntity(person);
   * var vo = mapper.convertEntityToVo(repository.save(entity));
   * return vo;
   * }
   */

  public PersonVO update(PersonVO person) {

    var entity = repository.findById(person.getKey())
        .orElseThrow(null);

    entity.setFirstName(person.getFirstName());
    entity.setLastName(person.getLastName());
    entity.setAddress(person.getAddress());
    entity.setGender(person.getGender());

    var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
    vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());

    return vo;
  }

  public void delete(Long id) {
    var entity = repository.findById(id)
        .orElseThrow(null);

    repository.delete(entity);
  }

}
