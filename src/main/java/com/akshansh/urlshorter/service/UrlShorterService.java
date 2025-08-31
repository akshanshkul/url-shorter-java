package com.akshansh.urlshorter.service;

import com.akshansh.urlshorter.dto.UrlResponseDTO;
import com.akshansh.urlshorter.model.UrlClickAnalytics;
import com.akshansh.urlshorter.model.UrlShorterModel;
import com.akshansh.urlshorter.repository.UrlClickAnalyticsRepository;
import com.akshansh.urlshorter.repository.UrlShorterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class UrlShorterService {

    @Autowired
    private UrlShorterRepository urlShorterRepository;

    @Autowired
    private UrlClickAnalyticsRepository urlClickAnalyticsRepository;

    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int SHORT_CODE_LENGTH = 8;
    private final Random random = new Random();




    // Generate random 8-character short code
    private String generateShortCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SHORT_CODE_LENGTH; i++) {
            sb.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
        }
        return sb.toString();
    }

    // Save a new short URL mapping
    public UrlResponseDTO createShortUrl(String longUrl) {
        String shortCode;
        // Ensure uniqueness of shortCode
        do {
            shortCode = generateShortCode();
        } while (urlShorterRepository.findByShortCode(shortCode).isPresent());

        UrlShorterModel url = new UrlShorterModel(shortCode, longUrl);
        UrlShorterModel savedUrl = urlShorterRepository.save(url);
        UrlResponseDTO dto = new UrlResponseDTO(savedUrl.getShortCode());

        return dto;
    }

    // Find long URL by short code
    public Optional<UrlShorterModel> getLongUrlByShortCode(String shortCode) {
        return urlShorterRepository.findByShortCode(shortCode);
    }

    // Record click analytics
    public void recordClick(UrlShorterModel url, String ipAddress, String userAgent) {
        UrlClickAnalytics analytics = new UrlClickAnalytics(
                url,
                LocalDateTime.now(),
                ipAddress,
                userAgent
        );
        urlClickAnalyticsRepository.save(analytics);
    }

    // Get total clicks for a URL
    public long getClickCount(UrlShorterModel url) {
        return urlClickAnalyticsRepository.countByUrl(url);
    }
}
