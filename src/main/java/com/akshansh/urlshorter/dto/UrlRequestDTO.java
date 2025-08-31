package com.akshansh.urlshorter.dto;

public class UrlRequestDTO {

    private String longUrl;

    public UrlRequestDTO() {}

    public UrlRequestDTO(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
