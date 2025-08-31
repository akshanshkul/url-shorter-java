package com.akshansh.urlshorter.repository;

import com.akshansh.urlshorter.model.UrlShorterModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlShorterRepository extends JpaRepository<UrlShorterModel, Long> {
    Optional<UrlShorterModel> findByShortCode(String shortCode);
}
