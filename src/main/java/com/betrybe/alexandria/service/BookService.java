package com.betrybe.alexandria.service;

import com.betrybe.alexandria.entity.Author;
import com.betrybe.alexandria.entity.Book;
import com.betrybe.alexandria.entity.BookDetail;
import com.betrybe.alexandria.entity.Publisher;
import com.betrybe.alexandria.exception.BookDetailNotFoundException;
import com.betrybe.alexandria.exception.BookNotFoundException;
import com.betrybe.alexandria.repository.BookDetailRepository;
import com.betrybe.alexandria.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {
  @Autowired
  private BookRepository repository;

  @Autowired
  private BookDetailRepository detailRepository;

  @Autowired
  private PublisherService publisherService;

  @Autowired
  private AuthorService authorService;

  public Book findById(Long id) {
    return repository.findById(id)
        .orElseThrow(BookNotFoundException::new);
  }

  public List<Book> findAll(int pageNumber, int pageSize) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    Page<Book> page = repository.findAll(pageable);

    return page.toList();
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

  public Book addBookAuthor(Long bookId, Long authorId) {
    Book book = findById(bookId);
    Author author = authorService.findById(authorId);

    book.getAuthors().add(author);

    return repository.save(book);
  }

  public Book removeBookAuthor(Long bookId, Long authorId) {
    Book book = findById(bookId);
    Author author = authorService.findById(authorId);

    book.getAuthors().remove(author);

    return repository.save(book);
  }

}
