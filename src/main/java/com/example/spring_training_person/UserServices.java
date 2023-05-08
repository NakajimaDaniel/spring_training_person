package com.example.spring_training_person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserServices implements UserDetailsService {

  @Autowired
  UserRepository repository;

  public UserServices(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    var user = repository.findByUsername(username);
    if (user != null) {
      return user;
    } else {
      return null;
    }

  }

}
