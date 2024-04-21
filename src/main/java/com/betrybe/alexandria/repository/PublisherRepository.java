package com.betrybe.alexandria.repository;

import com.betrybe.alexandria.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

}
