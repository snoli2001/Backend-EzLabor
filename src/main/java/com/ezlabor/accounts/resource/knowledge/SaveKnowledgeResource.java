package com.ezlabor.accounts.resource.knowledge;

import com.ezlabor.accounts.domain.model.background.Knowledge;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveKnowledgeResource {
    @NotNull
    @NotBlank
    @Size(min = 2,max = 20)
    private String name;
    @Size(min = 2,max = 200)
    private String description;
    @NotNull
    @NotBlank
    private Knowledge.KnowledgeLevel level;
}
