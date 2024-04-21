package com.betrybe.alexandria.service;

import com.betrybe.alexandria.entity.Author;
import com.betrybe.alexandria.entity.Publisher;
import com.betrybe.alexandria.exception.AuthorNotFoundException;
import com.betrybe.alexandria.exception.PublisherNotFoundException;
import com.betrybe.alexandria.repository.PublisherRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {
  @Autowired
  private PublisherRepository repository;

  public Publisher findById(Long id) {
    return repository.findById(id)
        .orElseThrow(PublisherNotFoundException::new);
  }

  public List<Publisher> findAll() {
    return repository.findAll();
  }

  public Publisher create(Publisher publisher) {
    return repository.save(publisher);
  }

  public Publisher update(Long id, Publisher publisher) {
    Publisher publisherDb = findById(id);

    publisherDb.setName(publisher.getName());
    publisherDb.setAddress(publisher.getAddress());

    return repository.save(publisherDb);
  }

  public Publisher deleteById(Long id) {
    Publisher publisher = findById(id);

    repository.deleteById(id);

    return publisher;
  }

}
