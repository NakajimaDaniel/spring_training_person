package com.example.spring_training_person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authentication Endpoint")
@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  AuthServices authServices;

  @Operation(summary = "Authenticates a user and returns a token")
  @PostMapping(value = "/signin")
  @SuppressWarnings("rawtypes")
  public ResponseEntity signin(@RequestBody AccountCredentialsVO data) throws Exception {
    if (data == null || data.getUsername() == null || data.getUsername().isBlank() || data.getPassword() == null
        || data.getPassword().isBlank())
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");

    var token = authServices.signin(data);

    if (token == null)
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");
    return token;
  }

  @Operation(summary = "Refresh token for authenticated user and returns a token")
  @PutMapping(value = "/refresh/{username}")
  @SuppressWarnings("rawtypes")
  public ResponseEntity refreshToken(@PathVariable("username") String username,
      @RequestHeader("Authorization") String refreshToken) throws Exception {
    if (refreshToken == null || refreshToken.isBlank() || username == null || username.isBlank())
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");

    var token = authServices.refreshToken(username, refreshToken);

    if (token == null)
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");
    return token;
  }
}
