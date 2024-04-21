package com.betrybe.alexandria.service;

import com.betrybe.alexandria.entity.Author;
import com.betrybe.alexandria.exception.AuthorNotFoundException;
import com.betrybe.alexandria.repository.AuthorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

  @Autowired
  private AuthorRepository repository;

  public Author findById(Long id) {
    return repository.findById(id)
        .orElseThrow(AuthorNotFoundException::new);
  }

  public List<Author> findAll() {
    return repository.findAll();
  }

  public Author create(Author author) {
    return repository.save(author);
  }

  public Author update(Long id, Author author) {
    Author authorDb = findById(id);

    authorDb.setName(author.getName());
    authorDb.setNationality(author.getNationality());

    return repository.save(authorDb);
  }

  public Author deleteById(Long id) {
    Author author = findById(id);

    repository.deleteById(id);

    return author;
  }

}
