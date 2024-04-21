package com.betrybe.alexandria.controller.dto;

import com.betrybe.alexandria.entity.Book;

public record BookCreationDto(String title, String genre) {

  public static Book toEntity(BookCreationDto bookCreationDto) {
    return new Book(bookCreationDto.title(), bookCreationDto.genre());
  }

}
