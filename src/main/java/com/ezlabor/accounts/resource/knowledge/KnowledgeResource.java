package com.ezlabor.accounts.resource.knowledge;
import com.ezlabor.accounts.domain.model.background.Knowledge;
import lombok.Data;

@Data
public class KnowledgeResource {
    private Long id;
    private String name;
    private String description;
    private Knowledge.KnowledgeLevel level;
}
