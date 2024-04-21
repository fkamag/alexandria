package com.betrybe.alexandria.controller.dto;

import com.betrybe.alexandria.entity.Author;

public record AuthorCreationDto(String name, String nationality) {

  public static Author toEntity(AuthorCreationDto authorCreationDto) {
    return new Author(authorCreationDto.name(), authorCreationDto.nationality());
  }

}
