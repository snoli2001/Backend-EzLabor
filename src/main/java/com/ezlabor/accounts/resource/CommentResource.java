package com.ezlabor.accounts.resource;

public class CommentResource {
    private Long id;
    private String content;

    public Long getId() {
        return id;
    }

    public CommentResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public CommentResource setContent(String content) {
        this.content = content;
        return this;
    }
}
