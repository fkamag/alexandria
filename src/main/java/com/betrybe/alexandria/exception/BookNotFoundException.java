package com.betrybe.alexandria.exception;

public class BookNotFoundException extends RuntimeException{

  public BookNotFoundException() {
    super("Livro não encontrado");
  }

}
