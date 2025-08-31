package com.akshansh.urlshorter.dto;

public class UrlResponseDTO {

    private String shortUrl;
    public UrlResponseDTO() {}

    public UrlResponseDTO(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

}
