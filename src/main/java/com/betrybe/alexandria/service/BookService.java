package com.betrybe.alexandria.service;

import com.betrybe.alexandria.entity.Book;
import com.betrybe.alexandria.entity.BookDetail;
import com.betrybe.alexandria.entity.Publisher;
import com.betrybe.alexandria.exception.BookDetailNotFoundException;
import com.betrybe.alexandria.exception.BookNotFoundException;
import com.betrybe.alexandria.repository.BookDetailRepository;
import com.betrybe.alexandria.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
  @Autowired
  private BookRepository repository;

  @Autowired
  private BookDetailRepository detailRepository;

  @Autowired
  private PublisherService publisherService;

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

  public BookDetail createDetail(Long bookId, BookDetail bookDetail) {
    Book book = findById(bookId);
    bookDetail.setBook(book);
    return detailRepository.save(bookDetail);
  }

  public BookDetail getBookDetail(Long bookId) {
    Book book = findById(bookId);
    BookDetail bookDetail = book.getDetail();
    if (bookDetail == null) {
      throw new BookDetailNotFoundException();
    }
    return bookDetail;
  }

  public BookDetail updateDetail(Long bookId, BookDetail bookDetail) {
    BookDetail bookDetailDb = getBookDetail(bookId);
    bookDetailDb.setSummary(bookDetail.getSummary());
    bookDetailDb.setPageCount(bookDetail.getPageCount());
    bookDetailDb.setYear(bookDetail.getYear());
    bookDetailDb.setIsbn(bookDetail.getIsbn());

    return detailRepository.save(bookDetailDb);
  }

  public BookDetail deleteDetail(Long bookId) {
    Book book = findById(bookId);
    BookDetail bookDetail = book.getDetail();
    if (bookDetail == null) {
      throw new BookDetailNotFoundException();
    }
    book.setDetail(null);
    bookDetail.setBook(null);
    detailRepository.delete(bookDetail);

    return bookDetail;
  }

  public Book setBookPublisher(Long bookId, Long publisherId) {
    Book book = findById(bookId);
    Publisher publisher = publisherService.findById(publisherId);

    book.setPublisher(publisher);

    return repository.save(book);
  }

  public Book removeBookPublisher(Long bookId) {
    Book book = findById(bookId);

    book.setPublisher(null);

    return repository.save(book);
  }

}
