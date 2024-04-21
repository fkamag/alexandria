package com.betrybe.alexandria.exception;

public class PublisherNotFoundException extends RuntimeException {

  public PublisherNotFoundException() {
    super("Editora não encontrada");
  }
}
