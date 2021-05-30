package com.ezlabor.accounts.domain.model;

import com.ezlabor.common.model.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "publications")
@Data
public class Publication extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    public Long getId() {
        return id;
    }

    public Publication setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getFormatId() {
        return formatId;
    }

    public Publication setFormatId(Long formatId) {
        this.formatId = formatId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Publication setContent(String content) {
        this.content = content;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public Publication setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Publication setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Publication setUser(User user) {
        this.user = user;
        return this;
    }
}
