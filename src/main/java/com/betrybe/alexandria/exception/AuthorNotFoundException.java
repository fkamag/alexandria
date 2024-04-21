package com.betrybe.alexandria.exception;

public class AuthorNotFoundException extends RuntimeException {
  public AuthorNotFoundException() {
    super("Autor não encontrado");
  }
}
