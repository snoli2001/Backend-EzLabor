package com.ezlabor.accounts.resource;

public class PublicationResource {
    private Long id;
    private Long formatId;
    private String content;
    private String videoUrl;
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public PublicationResource setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getFormatId() {
        return formatId;
    }

    public PublicationResource setFormatId(Long formatId) {
        this.formatId = formatId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public PublicationResource setContent(String content) {
        this.content = content;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public PublicationResource setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public PublicationResource setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
