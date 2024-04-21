package com.betrybe.alexandria.controller;

import com.betrybe.alexandria.controller.dto.AuthorCreationDto;
import com.betrybe.alexandria.controller.dto.AuthorDto;
import com.betrybe.alexandria.entity.Author;
import com.betrybe.alexandria.service.AuthorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController {

  @Autowired
  private AuthorService service;

  @GetMapping("/{id}")
  public ResponseEntity<AuthorDto> findById(@PathVariable Long id) {
    Author author = service.findById(id);
    AuthorDto authorDto = AuthorDto.fromEntity(author);
    return ResponseEntity.status(HttpStatus.OK).body(authorDto);
  }

  @GetMapping
  public ResponseEntity<List<AuthorDto>> findAll() {
    List<Author> authors = service.findAll();
    List<AuthorDto> authorDtos = authors.stream()
        .map(AuthorDto::fromEntity)
        .toList();
    return ResponseEntity.status(HttpStatus.OK).body(authorDtos);
  }

  @PostMapping
  public ResponseEntity<AuthorDto> create(@RequestBody AuthorCreationDto authorCreationDto) {
    Author author = AuthorCreationDto.toEntity(authorCreationDto);
    Author authorDb = service.create(author);
    AuthorDto authorDto = AuthorDto.fromEntity(authorDb);
    return ResponseEntity.status(HttpStatus.CREATED).body(authorDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<AuthorDto> update(@PathVariable Long id,
      @RequestBody AuthorCreationDto authorCreationDto) {
    Author author = AuthorCreationDto.toEntity(authorCreationDto);
    Author authorDb = service.update(id, author);
    AuthorDto authorDto = AuthorDto.fromEntity(authorDb);
    return ResponseEntity.status(HttpStatus.OK).body(authorDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<AuthorDto> deleteById(@PathVariable Long id) {
    AuthorDto authorDto = AuthorDto.fromEntity(service.deleteById(id));
    return ResponseEntity.status(HttpStatus.OK).body(authorDto);
  }

}
