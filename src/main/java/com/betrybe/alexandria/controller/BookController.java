package com.betrybe.alexandria.controller;

import com.betrybe.alexandria.controller.dto.BookCreationDto;
import com.betrybe.alexandria.controller.dto.BookDetailCreationDto;
import com.betrybe.alexandria.controller.dto.BookDetailDto;
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
import org.springframework.web.bind.annotation.ResponseStatus;
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

  @PostMapping("/{bookId}/detail")
  @ResponseStatus(HttpStatus.CREATED)
  public BookDetailDto createDetail(@PathVariable Long bookId,
      @RequestBody BookDetailCreationDto bookDetailCreationDto) {
    return BookDetailDto.fromEntity(
        service.createDetail(bookId, bookDetailCreationDto.toEntity())
    );
  }

  @GetMapping("/{bookId}/detail")
  public BookDetailDto getBookDetail(@PathVariable Long bookId) {
    return BookDetailDto.fromEntity(
        service.getBookDetail(bookId)
    );
  }

  @PutMapping("/{bookId}/detail")
  public ResponseEntity<BookDetailDto> updateDetail(@PathVariable Long bookId,
      @RequestBody BookDetailCreationDto bookDetailCreationDto) {
    BookDetailDto bookDetailDto =BookDetailDto.fromEntity(
        service.updateDetail(bookId, bookDetailCreationDto.toEntity())
    );
    return ResponseEntity.status(HttpStatus.OK).body(bookDetailDto);
  }

  @DeleteMapping("/{bookId}/detail")
  public BookDetailDto deleteDetail(@PathVariable Long bookId) {
    return BookDetailDto.fromEntity(
        service.deleteDetail(bookId)
    );
  }

  @PutMapping("/{bookId}/publisher/{publisherId}")
  public BookDto setBookPublisher(@PathVariable Long bookId,@PathVariable Long publisherId) {
    return BookDto.fromEntity(service.setBookPublisher(bookId, publisherId));
  }

  @DeleteMapping("/{bookId}/publisher")
  public BookDto removeBookPublisher(@PathVariable Long bookId) {
    return BookDto.fromEntity(service.removeBookPublisher(bookId));
  }

  @PutMapping("/{bookId}/authors/{authorId}")
  public BookDto addBookAuthor(@PathVariable Long bookId, @PathVariable Long authorId) {
    return BookDto.fromEntity(service.addBookAuthor(bookId, authorId));
  }

  @DeleteMapping("/{bookId}/authors/{authorId}")
  public BookDto removeBookAuthor(@PathVariable Long bookId, @PathVariable Long authorId) {
    return BookDto.fromEntity(service.removeBookAuthor(bookId, authorId));
  }

}
