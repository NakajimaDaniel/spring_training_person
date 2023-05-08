package com.example.spring_training_person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "endpoint for managing people")
public class PersonController {

  @Autowired
  private PersonServices service;

  @GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
  @Operation(summary = "Find all people", description = "Find all people", tags = { "People" }, responses = {
      @ApiResponse(description = "Success", responseCode = "200", content = @Content),
      @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
      @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
      @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
      @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)

  })

  public ResponseEntity<PagedModel<EntityModel<PersonVO>>> findAll(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "limit", defaultValue = "12") Integer limit,
      @RequestParam(value = "direction", defaultValue = "asc") String direction) {

    var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
    Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));
    return ResponseEntity.ok(service.findAll(pageable));
  }

  @CrossOrigin(origins = "http://localhost:8080")
  @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
      MediaType.APPLICATION_YML })
  @Operation(summary = "Find a person", description = "Find a person", tags = { "People" }, responses = {
      @ApiResponse(description = "Success", responseCode = "200", content = @Content),
      @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
      @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
      @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
      @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
      @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)

  })

  public PersonVO findById(@PathVariable Long id) {
    return service.findById(id);
  }

  @CrossOrigin(origins = "http://localhost:8080")
  @PostMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
      MediaType.APPLICATION_YML }, produces = {
          MediaType.APPLICATION_JSON, com.example.spring_training_person.MediaType.APPLICATION_XML,
          MediaType.APPLICATION_YML })

  @Operation(summary = "Insert a person", description = "Insert a person", tags = { "People" }, responses = {
      @ApiResponse(description = "Success", responseCode = "200", content = @Content),
      @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
      @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
      @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
      @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
      @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)

  })

  public PersonVO create(@RequestBody PersonVO person) {
    return service.create(person);
  }
  /*
   * @PostMapping(value = "/v2", consumes = { MediaType.APPLICATION_JSON,
   * MediaType.APPLICATION_XML, MediaType.APPLICATION_YML }, produces = {
   * MediaType.APPLICATION_JSON,
   * MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
   * public PersonVO2 createV2(@RequestBody PersonVO2 person) {
   * return service.createV2(person);
   * }
   */

  @PutMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
      MediaType.APPLICATION_YML }, produces = {
          MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })

  @Operation(summary = "Update person", description = "Update person", tags = { "People" }, responses = {
      @ApiResponse(description = "Success", responseCode = "200", content = @Content),
      @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
      @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
      @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
      @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
      @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)

  })

  public PersonVO update(@RequestBody PersonVO person) {
    return service.create(person);
  }

  @DeleteMapping("/{id}")

  @Operation(summary = "Delete a person", description = "Delete a person", tags = { "People" }, responses = {
      @ApiResponse(description = "Success", responseCode = "200", content = @Content),
      @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
      @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
      @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
      @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
      @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)

  })
  public ResponseEntity<?> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

}
