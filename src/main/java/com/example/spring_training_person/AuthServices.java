package com.example.spring_training_person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenProvider tokenProvider;

  @Autowired
  private UserRepository repository;

  @SuppressWarnings("rawtypes")
  public ResponseEntity signin(AccountCredentialsVO data) throws Exception {
    try {

      var username = data.getUsername();
      var password = data.getPassword();
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
      var user = repository.findByUsername(username);

      var tokenResponse = new TokenVO();
      if (user != null) {
        tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
      } else {
        throw new Exception();
      }
      return ResponseEntity.ok(tokenResponse);
    } catch (Exception e) {
      throw new Exception(e);
    }
  }

  @SuppressWarnings("rawtypes")
  public ResponseEntity refreshToken(String username, String refreshToken) throws Exception {
    var user = repository.findByUsername(username);

    var tokenResponse = new TokenVO();
    if (user != null) {
      tokenResponse = tokenProvider.refreshToken(refreshToken);
    } else {
      throw new Exception();
    }
    return ResponseEntity.ok(tokenResponse);
  }
}
