package com.betrybe.alexandria.exception;

public class BookDetailNotFoundException extends RuntimeException {

  public BookDetailNotFoundException() {
    super("Detalhes do livro não encontrados");
  }
}
