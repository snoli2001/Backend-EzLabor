package com.ezlabor.hiring.resource.requirement;

import com.ezlabor.common.SkillLevel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SaveRequirementResource {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private SkillLevel level;
}
