package com.ezlabor.accounts.resource.knowledge;
import com.ezlabor.accounts.domain.model.background.Knowledge;
import com.ezlabor.common.SkillLevel;
import lombok.Data;

@Data
public class KnowledgeResource {
    private Long id;
    private String name;
    private String description;
    private SkillLevel level;
}
