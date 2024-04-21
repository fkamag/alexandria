package com.betrybe.alexandria.controller.dto;

import com.betrybe.alexandria.entity.Publisher;

public record PublisherCreationDto(String name, String address) {

  public static Publisher toEntity(PublisherCreationDto publisherCreationDto) {
    return new Publisher(publisherCreationDto.name(), publisherCreationDto.address());
  }

}
