package com.betrybe.alexandria.service;

import com.betrybe.alexandria.entity.Book;
import com.betrybe.alexandria.exception.BookNotFoundException;
import com.betrybe.alexandria.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
  @Autowired
  private BookRepository repository;

  public Book findById(Long id) {
    return repository.findById(id)
        .orElseThrow(BookNotFoundException::new);
  }

  public List<Book> findAll() {
    return repository.findAll();
  }

  public Book create(Book book) {
    return repository.save(book);
  }

  public Book update(Long id, Book book) throws BookNotFoundException {
    Book bookDb = findById(id);

    bookDb.setTitle(book.getTitle());
    bookDb.setGenre(book.getGenre());

    return repository.save(bookDb);
  }

  public Book deleteById(Long id) throws BookNotFoundException {
    Book book = findById(id);

    repository.deleteById(id);

    return book;
  }

}
