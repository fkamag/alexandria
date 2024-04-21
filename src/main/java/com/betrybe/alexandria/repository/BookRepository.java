package com.betrybe.alexandria.repository;

import com.betrybe.alexandria.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
