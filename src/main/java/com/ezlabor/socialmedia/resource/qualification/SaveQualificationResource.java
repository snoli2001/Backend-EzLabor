package com.ezlabor.socialmedia.resource.qualification;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SaveQualificationResource {
    @NotNull
    @NotBlank
    private String comment;
    @NotNull
    @NotBlank
    private Integer stars;
}
