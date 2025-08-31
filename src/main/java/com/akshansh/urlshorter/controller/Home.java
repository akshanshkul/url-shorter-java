package com.akshansh.urlshorter.controller;

import com.akshansh.urlshorter.dto.UrlRequestDTO;
import com.akshansh.urlshorter.model.UrlShorterModel;
import com.akshansh.urlshorter.service.UrlShorterService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class Home {
    // Create short URL
    @GetMapping("")
    public ResponseEntity<Map<String, String>> shortenUrl() {
        Map<String, String> message = new LinkedHashMap<>();
        message.put("service", "URL Shortener");
        message.put("message", "Welcome to the URL Shortener Service.");
        message.put("status", "Running");
        message.put("version", "1.0.0");
        message.put("author", "Akshansh");
        message.put("time", LocalDateTime.now().toString());
        return ResponseEntity.ok(message);
    }


}
