package com.ezlabor.accounts.resource;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SavePublicationResource {

    @NotNull
    @NotBlank
    private Long formatId;

    @NotNull
    @NotBlank
    @Lob
    private String content;

    @NotNull
    @NotBlank
    private String videoUrl;

    @NotNull
    @NotBlank
    private String imageUrl;

    public Long getFormatId() {
        return formatId;
    }

    public SavePublicationResource setFormatId(Long formatId) {
        this.formatId = formatId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public SavePublicationResource setContent(String content) {
        this.content = content;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public SavePublicationResource setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public SavePublicationResource setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
