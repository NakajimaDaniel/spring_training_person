package com.example.spring_training_person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Service
public class PersonServices {

  @Autowired
  PersonRepository repository;

  @Autowired
  PersonMapper mapper;
  @Autowired
  PagedResourcesAssembler<PersonVO> assembler;

  public PagedModel<EntityModel<PersonVO>> findAll(Pageable pageable) {

    var personPage = repository.findAll(pageable);

    var personVosPage = personPage.map(p -> DozerMapper.parseObject(p, PersonVO.class));

    personVosPage
        .map(p -> p.add(linkTo(methodOn(PersonController.class).findById(((PersonVO) p).getKey())).withSelfRel()));

    Link link = linkTo(
        methodOn(PersonController.class).findAll(pageable.getPageNumber(), pageable.getPageSize(), "asc"))
        .withSelfRel();
    return assembler.toModel(personVosPage, link);
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
