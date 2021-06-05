package com.ezlabor.socialmedia.resource.qualification;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class QualificationResource {
    private String comment;
    private Integer stars;
}
