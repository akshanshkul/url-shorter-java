package com.akshansh.urlshorter.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "url_click_analytics")
public class UrlClickAnalytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link each click to a URL mapping
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "url_id", nullable = false)
    private UrlShorterModel url;

    @Column(name = "clicked_at", nullable = false)
    private LocalDateTime clickedAt;

    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    @Column(name = "user_agent", length = 512)
    private String userAgent;

    // Constructors
    public UrlClickAnalytics() {}

    public UrlClickAnalytics(UrlShorterModel url, LocalDateTime clickedAt, String ipAddress, String userAgent) {
        this.url = url;
        this.clickedAt = clickedAt;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public UrlShorterModel getUrl() {
        return url;
    }

    public void setUrl(UrlShorterModel url) {
        this.url = url;
    }

    public LocalDateTime getClickedAt() {
        return clickedAt;
    }

    public void setClickedAt(LocalDateTime clickedAt) {
        this.clickedAt = clickedAt;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
