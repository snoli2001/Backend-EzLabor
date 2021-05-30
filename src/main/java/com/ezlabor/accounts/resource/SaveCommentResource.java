package com.ezlabor.accounts.resource;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SaveCommentResource {

    @NotNull
    @NotBlank
    @Lob
    private String content;

    public String getContent() {
        return content;
    }

    public SaveCommentResource setContent(String content) {
        this.content = content;
        return this;
    }
}
