package com.betrybe.alexandria.controller;

import com.betrybe.alexandria.controller.dto.BookCreationDto;
import com.betrybe.alexandria.controller.dto.BookDto;
import com.betrybe.alexandria.entity.Book;
import com.betrybe.alexandria.service.BookService;
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
@RequestMapping("/books")
public class BookController {

  @Autowired
  private BookService service;

  @GetMapping("/{id}")
  public ResponseEntity<BookDto> findById(@PathVariable Long id) {
    Book book = service.findById(id);
    BookDto bookDto = BookDto.fromEntity(book);
    return ResponseEntity.status(HttpStatus.OK).body(bookDto);
  }

  @GetMapping
  public ResponseEntity<List<BookDto>> findAll() {
    List<Book> books = service.findAll();
    List<BookDto> bookDtos = books.stream()
        .map(BookDto::fromEntity)
        .toList();
    return ResponseEntity.status(HttpStatus.OK).body(bookDtos);
  }

  @PostMapping
  public ResponseEntity<BookDto> create(@RequestBody BookCreationDto bookCreationDto) {
    Book book = BookCreationDto.toEntity(bookCreationDto);
    Book bookDb = service.create(book);
    BookDto bookDto = BookDto.fromEntity(bookDb);
    return ResponseEntity.status(HttpStatus.CREATED).body(bookDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<BookDto> update(@PathVariable Long id, @RequestBody BookCreationDto bookCreationDto) {
    Book book = BookCreationDto.toEntity(bookCreationDto);
    Book bookDb = service.update(id, book);
    BookDto bookDto = BookDto.fromEntity(bookDb);
    return ResponseEntity.status(HttpStatus.OK).body(bookDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BookDto> deleteById(@PathVariable Long id) {
    BookDto bookDto = BookDto.fromEntity(service.deleteById(id));
    return ResponseEntity.status(HttpStatus.OK).body(bookDto);
  }

}
