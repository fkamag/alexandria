package com.betrybe.alexandria.controller;

import com.betrybe.alexandria.controller.dto.PublisherCreationDto;
import com.betrybe.alexandria.controller.dto.PublisherDto;
import com.betrybe.alexandria.entity.Publisher;
import com.betrybe.alexandria.service.PublisherService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

  @Autowired
  private PublisherService service;

  @GetMapping("/{id}")
  public ResponseEntity<PublisherDto> findById(@PathVariable Long id) {
    Publisher publisher = service.findById(id);
    PublisherDto publisherDto = PublisherDto.fromEntity(publisher);
    return ResponseEntity.status(HttpStatus.OK).body(publisherDto);
  }

  @GetMapping
  public ResponseEntity<List<PublisherDto>> findAll() {
    List<Publisher> publishers = service.findAll();
    List<PublisherDto> publisherDtos = publishers.stream()
        .map(PublisherDto::fromEntity)
        .toList();
    return ResponseEntity.status(HttpStatus.OK).body(publisherDtos);
  }

  @PostMapping
  public ResponseEntity<PublisherDto> create(@RequestBody PublisherCreationDto publisherCreationDto) {
    Publisher publisher = PublisherCreationDto.toEntity(publisherCreationDto);
    Publisher publisherDb = service.create(publisher);
    PublisherDto publisherDto = PublisherDto.fromEntity(publisherDb);
    return ResponseEntity.status(HttpStatus.CREATED).body(publisherDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PublisherDto> update(@PathVariable Long id,
      @RequestBody PublisherCreationDto publisherCreationDto) {
    Publisher publisher = PublisherCreationDto.toEntity(publisherCreationDto);
    Publisher publisherDb = service.update(id, publisher);
    PublisherDto publisherDto = PublisherDto.fromEntity(publisherDb);
    return ResponseEntity.status(HttpStatus.OK).body(publisherDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<PublisherDto> deleteById(@PathVariable Long id) {
    PublisherDto publisherDto = PublisherDto.fromEntity(service.deleteById(id));
    return ResponseEntity.status(HttpStatus.OK).body(publisherDto);
  }

}
