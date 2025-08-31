package com.akshansh.urlshorter.repository;

import com.akshansh.urlshorter.model.UrlClickAnalytics;
import com.akshansh.urlshorter.model.UrlShorterModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlClickAnalyticsRepository extends JpaRepository<UrlClickAnalytics, Long> {
    long countByUrl(UrlShorterModel url);
}
