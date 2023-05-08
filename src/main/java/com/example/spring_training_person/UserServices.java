package com.example.spring_training_person;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Service
public class UserServices implements UserDetails {

  @Autowired
  UserRepository repository;

  public UserServices(UserRepository repository) {
    this.repository = repository;
  }

  public UserDetails loadUserByUsername(String username) throws Exception {
    var user = repository.findByUsername(username);
    if (user != null) {
      return user;
    } else {
      throw new Exception();
    }
  }

}
