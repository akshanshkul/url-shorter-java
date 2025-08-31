package com.akshansh.urlshorter.controller;

import com.akshansh.urlshorter.dto.UrlRequestDTO;
import com.akshansh.urlshorter.dto.UrlResponseDTO;
import com.akshansh.urlshorter.model.UrlShorterModel;
import com.akshansh.urlshorter.service.UrlShorterService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/url")
public class UrlShorterController {

    @Autowired
    private UrlShorterService urlShorterService;

    @Value("${server.port}")
    private int serverPort;

    @Value("${app.host}")
    private String rootUrl;


    // Create short URL
    @PostMapping("/shorten")
    public ResponseEntity<UrlResponseDTO> shortenUrl(@RequestBody UrlRequestDTO urlRequest) {
        UrlResponseDTO savedUrlData = urlShorterService.createShortUrl(urlRequest.getLongUrl());
        savedUrlData.setShortUrl(rootUrl+":"+serverPort+"/"+savedUrlData.getShortUrl());
        return ResponseEntity.ok(savedUrlData);
    }

    // Redirect short URL to long URL and record click
    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode, HttpServletRequest request) {
        Optional<UrlShorterModel> urlOpt = urlShorterService.getLongUrlByShortCode(shortCode);

        if (urlOpt.isPresent()) {
            UrlShorterModel url = urlOpt.get();

            // Record click analytics
            String ip = request.getRemoteAddr();
            String userAgent = request.getHeader("User-Agent");
            urlShorterService.recordClick(url, ip, userAgent);

            return ResponseEntity.status(302)  // HTTP 302 redirect
                    .location(URI.create(url.getLongUrl()))
                    .build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{shortCode}/info")
    public ResponseEntity<UrlResponseDTO> fetchUrl(@PathVariable String shortCode, HttpServletRequest request) {
        return urlShorterService.getLongUrlByShortCode(shortCode)
                .map(url -> {
                    // Record click analytics
                    String ip = request.getRemoteAddr();
                    String userAgent = request.getHeader("User-Agent");
                    urlShorterService.recordClick(url, ip, userAgent);

                    return ResponseEntity.ok(new UrlResponseDTO(url.getLongUrl()));
                })
                .orElse(ResponseEntity.notFound().build());
    }



    // Optional: Get click count for a short code
    @GetMapping("/{shortCode}/clicks")
    public ResponseEntity<Long> getClickCount(@PathVariable String shortCode) {
        Optional<UrlShorterModel> urlOpt = urlShorterService.getLongUrlByShortCode(shortCode);
        if (urlOpt.isPresent()) {
            long clicks = urlShorterService.getClickCount(urlOpt.get());
            return ResponseEntity.ok(clicks);
        }
        return ResponseEntity.notFound().build();
    }
}
